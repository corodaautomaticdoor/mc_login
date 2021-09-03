package com.coroda.mclogin.util;

public class Constants {

    public final static String MAIN_PATH = "/login";
    public final static String ID = "/{id}";

    public final static String SAVE_VALUE="Metodo a traves del cual se envia la informacion del Login que  sera registrada dentro de la base de datos";
    public final static String SAVE_NOTE="Para el registro del Login , sera necesario el llenado de todo los campos , a excepcion de los Id´s los cuales seran generados automaticamente";
    public final static String DELETE_ID_VALUE="Metodo a traves del cual se Elimina la informacion del Login mediante su Id ";
    public final static String DELETE_ID_NOTE="Para eliminar los  datos del Login , sera necesario enviar el Id de la Operacion ";
    public final static String UPDATE_ID_VALUE="Metodo a traves del cual se Actualiza la informacion del Login mediante su Id ";
    public final static String UPDATE_ID_NOTE="Para Actualizar los  datos del Login , sera necesario enviar todo el registro con los datos ya actualizados junto a sus Id`s ";
    public final static String GET_ID_VALUE="Metodo a traves del cual se Obtiene la informacion del Login mediante su Id ";
    public final static String GET_ID_NOTE="Para la obtencion de datos del Login , sera necesario enviar el Id del Login a consultar";

    public final static String GET_FIND_ALL_VALUE="Metodo a traves del cual se Obtiene todos los registros del Login";
    public final static String GET_FIND_ALL_NOTE="Para la obtencion de datos del Login  no es necesario enviar ningun id ";

}