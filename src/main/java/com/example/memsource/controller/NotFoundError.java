package com.example.memsource.controller;

import java.util.NoSuchElementException;

/**
 * Exception to handle NoSuchElementException.
 */
public class NotFoundError extends NoSuchElementException {
    NotFoundErrorData data;

    public NotFoundError( String entity, Object descriptor) {
        super();
        this.data = new NotFoundErrorData(entity, descriptor);
    }

    public NotFoundErrorData getData() {
        return data;
    }

    public void setData(NotFoundErrorData data) {
        this.data = data;
    }

    /**
     * Data for NotFoundException. Represents JSON response.
     */
    public static class NotFoundErrorData {
        private String entity;
        private Object descriptor;

        public NotFoundErrorData(String entity, Object descriptor) {
            this.entity = entity;
            this.descriptor = descriptor;
        }

        public String getEntity() {
            return entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public Object getDescriptor() {
            return descriptor;
        }

        public void setDescriptor(Object descriptor) {
            this.descriptor = descriptor;
        }
    }

}
