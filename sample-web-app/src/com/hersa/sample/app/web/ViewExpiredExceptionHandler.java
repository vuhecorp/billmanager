package com.hersa.sample.app.web;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.RequestDispatcher;
 
public class ViewExpiredExceptionHandler extends ExceptionHandlerWrapper {
 
    private ExceptionHandler wrapped;
 
    public ViewExpiredExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }
 
    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }
 
    @Override
    public void handle() throws FacesException {
        for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();

            if (t instanceof ViewExpiredException) {
                ViewExpiredException vee = (ViewExpiredException) t;
                FacesContext fc = FacesContext.getCurrentInstance();
                Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
                NavigationHandler nav = fc.getApplication().getNavigationHandler();
                try {
                    requestMap.put("currentViewId", vee.getViewId());
                    requestMap.put(RequestDispatcher.ERROR_EXCEPTION, t);
                    requestMap.put(RequestDispatcher.ERROR_MESSAGE, vee.getViewId() + " has expired.");
                    nav.handleNavigation(fc, null, "error");
                    fc.renderResponse();
 
                } finally {
                    i.remove();
                }
            }
        }
        // At this point, the queue will not contain any ViewExpiredEvents.
        // Therefore, let the parent handle them.
        getWrapped().handle();
 
    }
}