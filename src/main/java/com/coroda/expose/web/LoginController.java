package com.coroda.expose.web;

import com.coroda.mclogin.bussines.LoginService;
import com.coroda.mclogin.model.api.request.Request;
import com.coroda.mclogin.model.api.response.Response;
import com.coroda.mclogin.util.Constants;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Constants.MAIN_PATH)
@Api(tags = "Microservicio Login", description = "Esta API se encarga de la gestion el Logeo de los usuarios")
@Slf4j
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    @ApiOperation(value = Constants.SAVE_VALUE, notes = Constants.SAVE_NOTE)
    public Completable save(@Valid @RequestBody Request request) {
        log.info("Envio de parametros");
        return loginService.save(request);
    }

    @DeleteMapping(Constants.ID)
    @ApiOperation(value = Constants.DELETE_ID_VALUE, notes = Constants.DELETE_ID_NOTE)
    public Completable delete(@PathVariable("id") Long operationId) {
        log.info("Eliminar datos por id");
        return loginService.delete(operationId);
    }

    @PutMapping(Constants.ID)
    @ApiOperation(value = Constants.UPDATE_ID_VALUE, notes = Constants.UPDATE_ID_NOTE)
    public Completable update(@RequestBody Request request) {
        log.info("Actualizacion de parametros");
        return loginService.update(request);
    }

    @GetMapping(Constants.ID)
    @ApiOperation(value = Constants.GET_ID_VALUE, notes = Constants.GET_ID_NOTE)
    public Single<Response> getById(@PathVariable("id") Long operationId) {
        log.info("Obtencion de datos por id");
        return loginService.getById(operationId);
    }

    @GetMapping
    @ApiOperation(value = Constants.GET_FIND_ALL_VALUE, notes = Constants.GET_FIND_ALL_NOTE)
    public Observable<Response> getFindAll() {
        log.info("Enviando parametros de busqueda");
        return loginService.findAll();
    }

    @PostMapping(Constants.VALIDATE)
    @ApiOperation(value = Constants.POST_VALIDATE_VALUE, notes = Constants.POST_VALIDATE_NOTE)
    public Maybe<Response> getValidate(@RequestBody Request request) {
        log.info("Valida el Datos del Usuario");
        return loginService.getValidate(request);
    }

}