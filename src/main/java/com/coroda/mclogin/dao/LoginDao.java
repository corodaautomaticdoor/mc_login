package com.coroda.mclogin.dao;

import com.coroda.mclogin.model.api.request.Request;
import com.coroda.mclogin.model.api.response.Response;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface LoginDao {

    Completable save(Request request);
    Completable delete(Long operationId);
    Completable update(Request request);
    Single<Response> getById(Long operationId);
    Observable<Response> findAll();

    Maybe<Response> validLogin(String email , String password);
}
