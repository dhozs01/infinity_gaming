package com.example.infinitygaming.excepciones;

public class ExcepcionInfinityGaming extends Exception {

    private Integer codigoError;
    private String mensajeErrorBD;
    private String mensajeErrorUsuario;
    private String sentenciaSQL;

    /**
     * Contructor por defecto de ExcepcionUsuario
     */
    public ExcepcionInfinityGaming() {
    }

    /**
     * Metodo para crear un objeto de la clase ExcepcionUsuario
     * @param codigoError codigo de error que manda la base de datos
     * @param mensajeErrorBD mensaje de error que manda la base de datos
     * @param mensajeErrorUsuario mensaje de error que ve el usuario
     * @param sentenciaSQL sentencia que se intenta lanzar ne la base de datos
     */
    public ExcepcionInfinityGaming(Integer codigoError, String mensajeErrorBD, String mensajeErrorUsuario, String sentenciaSQL) {
        this.codigoError = codigoError;
        this.mensajeErrorBD = mensajeErrorBD;
        this.mensajeErrorUsuario = mensajeErrorUsuario;
        this.sentenciaSQL = sentenciaSQL;
    }

    /**
     * Getter de codigoError
     * @return codigo de error que manda la base de datos
     */
    public Integer getCodigoError() {
        return codigoError;
    }

    /**
     * Setter de codigoError
     * @param codigoError codigo de error que le queremos establecer a la excepcion
     */
    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    /**
     * Getter de mensajeErrorBD
     * @return mensaje de error que manda la base de datos
     */
    public String getMensajeErrorBD() {
        return mensajeErrorBD;
    }

    /**
     * Setter de mensajeErrorBD
     * @param mensajeErrorBD mensaje de error de la base de datos que le queremos establecer a la excepcion
     */
    public void setMensajeErrorBD(String mensajeErrorBD) {
        this.mensajeErrorBD = mensajeErrorBD;
    }

    /**
     * Getter de mensajeErrorUsuario
     * @return mensaje de error que ve el usuario
     */
    public String getMensajeErrorUsuario() {
        return mensajeErrorUsuario;
    }

    /**
     * Setter de mensajeErrorUsuario
     * @param mensajeErrorUsuario mensaje de error de usuario que le queremos establecer a la excepcion
     */
    public void setMensajeErrorUsuario(String mensajeErrorUsuario) {
        this.mensajeErrorUsuario = mensajeErrorUsuario;
    }

    /**
     * Getter de sentenciaSQL
     * @return sentencia que se ha intentado lanzar en la base de datos
     */
    public String getSentenciaSQL() {
        return sentenciaSQL;
    }

    /**
     * Setter de sentenciaSQL
     * @param sentenciaSQL sentencia que le queremos establecer a la excepcion
     */
    public void setSentenciaSQL(String sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    @Override
    public String toString() {
        return "ExcepcionUsuario{" + "codigoError=" + codigoError + ", mensajeErrorBD=" + mensajeErrorBD + ", mensajeErrorUsuario=" + mensajeErrorUsuario + ", sentenciaSQL=" + sentenciaSQL + '}';
    }
}

