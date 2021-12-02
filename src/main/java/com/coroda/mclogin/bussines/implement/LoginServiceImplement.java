package com.coroda.mclogin.bussines.implement;

import com.coroda.mclogin.bussines.LoginService;
import com.coroda.mclogin.dao.LoginDao;
import com.coroda.mclogin.model.api.request.Request;
import com.coroda.mclogin.model.api.response.Response;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class LoginServiceImplement implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public Completable save(Request request) {
        return loginDao.save(request);
    }

    @Override
    public Completable delete(Long operationId) {
        return loginDao.delete(operationId);
    }

    @Override
    public Completable update(Request request) {
        return loginDao.update(request);
    }

    @Override
    public Single<Response> getById(Long operationId) {
        return loginDao.getById(operationId);
    }

    @Override
    public Observable<Response> findAll() {
        return loginDao.findAll();
    }

    @Override
    public Maybe<Response> getValidate(Request request) {
        return loginDao.validLogin(request.getEmail(),request.getPassword());
    }

    @Override
    public Maybe<Response> getData(Map<String, String> params) {
        log.info("Busqueda Dinamica");
        Maybe<Response> LoginResponse = null;
        String email = params.get("email");;
        String password = params.get("password");
        return loginDao.validLogin(email,password);
    }

}
