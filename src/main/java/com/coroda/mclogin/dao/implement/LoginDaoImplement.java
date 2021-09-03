package com.coroda.mclogin.dao.implement;

import com.coroda.mclogin.dao.LoginDao;
import com.coroda.mclogin.model.api.request.*;
import com.coroda.mclogin.model.api.response.Response;
import com.coroda.mclogin.model.entity.*;
import com.coroda.mclogin.repository.LoginRepository;
import io.reactivex.*;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.ws.rs.BadRequestException;

@Repository
@Slf4j
@Data
public class LoginDaoImplement implements LoginDao {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Completable save(Request request) {
        log.info("Guardando datos de login");
        return Single.fromCallable(() -> setOperacion(request))
                .map(loginRepository::save)
                .toCompletable();
    }

    @Override
    public Completable delete(Long loginId) {
        return maybeOperation(loginId)
                .flatMapCompletable(Operation ->
                {
                    loginRepository.deleteById(loginId);
                    return CompletableObserver::onComplete;
                });
    }

    private Maybe<Login> maybeOperation(Long operationId) {
        log.info("buscando por id y obteniendo los campos");
        return Maybe.just(
                loginRepository.findById(operationId)
                        .<BadRequestException>orElseThrow(BadRequestException::new))
                .switchIfEmpty(Maybe.empty());
    }
    @Override
    public Completable update(Request request) {
        log.info("actualizando y guardando los campos");
        return maybeOperation(request.getLoginId())
                .flatMapCompletable(operation -> save(request));
    }

    private Login setOperacion(Request model) {
        log.info("seteo de datos de login del metodo save");
        Login lo = new Login();
        lo.setLoginId(model.getLoginId());
        lo.setUserName(model.getUserName());
        lo.setEmail(model.getEmail());
        lo.setPassword(model.getPassword());
        return lo;
    }

    @Override
    public Observable<Response>  getById(Long loginId) {
        log.info("Extrayendo reistros del login acorde al Id");
        return Observable.fromIterable(loginRepository.searchId(loginId))
                .filter(obj -> obj.getLoginId().equals(loginId))
                .map(operacion -> getLogin(operacion))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Response> findAll() {
        log.info("seteo de todos los datos registrados");
        return Observable.fromIterable(loginRepository.findAll())
                .map(login -> getLogin(login))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Response> validLogin(String userName, String password) {
        log.info("Retorna comprobacion del Login");
        return Observable.fromIterable(loginRepository.validLogin(userName,password))
                .filter(objClient -> objClient.getUserName().equals(userName) &&
                        objClient.getPassword().equals(password))
                .map(login -> getLogin(login))
                .subscribeOn(Schedulers.io());
    }

    private Response getLogin(Login model)  {
        log.info("Extrayendo reistros de Login");
        Response rs = new Response();
        rs.setLoginId(model.getLoginId());
        rs.setEmail(model.getEmail());
        rs.setUserName(model.getUserName());
        rs.setPassword(model.getPassword());
        return rs;
    }

}
