package com.coroda.mclogin.dao;

import com.coroda.mclogin.model.api.request.Request;
import com.coroda.mclogin.model.api.response.Response;
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface LoginDao {

    Completable save(Request request);
    Completable delete(Long operationId);
    Completable update(Request request);
    Observable<Response> getById(Long operationId);
    Observable<Response> findAll();

    Observable<Response> validLogin(String userName ,String password);

}
