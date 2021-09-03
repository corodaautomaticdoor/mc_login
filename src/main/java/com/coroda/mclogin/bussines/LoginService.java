package com.coroda.mclogin.bussines;

import com.coroda.mclogin.model.api.request.Request;
import com.coroda.mclogin.model.api.response.Response;
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface LoginService {

    Completable save(Request request);
    Completable delete (Long operationId);
    Completable update (Request request);
    Observable<Response> getById (Long operationId);
    Observable<Response> findAll();
}
