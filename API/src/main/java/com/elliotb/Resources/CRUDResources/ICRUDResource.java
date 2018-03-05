package com.elliotb.Resources.CRUDResources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

public interface ICRUDResource<T> {

    Response getAll();

    Response getByID(String id);

    Response create(T t, @Context HttpHeaders httpHeaders);

    Response update(String id, T t);

    Response delete(String id);

}
