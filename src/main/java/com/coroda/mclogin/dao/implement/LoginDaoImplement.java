package com.coroda.mclogin.dao.implement;

import com.coroda.exception.DataException;
import com.coroda.exception.ResourceNotFoundException;
import com.coroda.mclogin.dao.LoginDao;
import com.coroda.mclogin.model.api.request.*;
import com.coroda.mclogin.model.api.response.Response;
import com.coroda.mclogin.model.entity.*;
import com.coroda.mclogin.model.thirtparthy.Person;
import com.coroda.mclogin.repository.LoginRepository;
import io.reactivex.*;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.BadRequestException;
import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
@Data
public class LoginDaoImplement implements LoginDao {


    @Autowired
    private RestTemplate clienteRest;

    @Autowired
    private LoginRepository loginRepository;

    @Value("${service-person.ribbon.listOfServer}")
    private String dataPerson;

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
        lo.setEmail(model.getEmail());
        lo.setPassword(model.getPassword());
        lo.setRolId(model.getRolId());
        return lo;
    }

    @Override
    public Single<Response>  getById(Long loginId) {
        log.info("Extrayendo reistros del login acorde al Id");
        return maybeLogin(loginId)
                .map(login -> getLogin(login))
                .toSingle();
//        return Observable.fromIterable(loginRepository.searchId(loginId))
//                .filter(obj -> obj.getLoginId().equals(loginId))
//                .map(operacion -> getLogin(operacion))
//                .subscribeOn(Schedulers.io());
    }
    private Maybe<Login> maybeLogin(Long loginId){
        log.info("buscando por id y obteniendo los campos");
        return Maybe.just(
                loginRepository.findById(loginId)
                        .<BadRequestException>orElseThrow(BadRequestException::new))
                .switchIfEmpty(Maybe.empty());
    }
    @Override
    public Observable<Response> findAll() {
        log.info("seteo de todos los datos registrados");
        return Observable.fromIterable(loginRepository.findAll())
                .map(login -> getLogin(login))
                .switchIfEmpty(Observable
                        .error( new ResourceNotFoundException("No se encontraron registros")))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Maybe<Response> validLogin(String email, String password) {
        log.info("Retorna comprobacion del Login");
        return Observable.fromIterable(loginRepository.validLogin(email,password))
                .switchIfEmpty(Observable
                        .error( new DataException("contraseña o correo erroneo")))
                .filter(objClient -> objClient.getEmail().equals(email) &&
                        objClient.getPassword().equals(password))
                .map(login -> getLogin(login))
                .firstElement()
                .subscribeOn(Schedulers.io());
    }

    private Response getLogin(Login model)  {
        log.info("Extrayendo reistros de Login");
        Response rs = new Response();
        rs.setLoginId(model.getLoginId());
        rs.setEmail(model.getEmail());
        rs.setRol(model.getRolId());
        rs.setInformation(getPerson(model.getEmail()));
        return rs;
    }
//    public List<Person> getPerson(String email) {
    public Person getPerson(String email) {
        Map<String,String> pathVariables= new HashMap<String,String>();
        pathVariables.put("email",email);
        log.info("Extrayendo registros de Cliente");
        Person person= clienteRest.getForObject(dataPerson, Person.class,pathVariables);
        return person;
    }

}
