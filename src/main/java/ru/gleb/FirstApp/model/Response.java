package ru.gleb.FirstApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    /***
     * Уникальный идентификатор сообщения
     */
    private String uid;

    /***
     * Уникальный идентификатор операции
     */
    private String operationUid;

    /***
     * Время создания сообщения
     */
    private String systemTime;

    /***
     * Код успешности выполения запроса
     */
    private Codes code;

    /***
     * Годовая премия
     */
    @JsonInclude(Include.NON_NULL)
    private Double annualBonus;

    /***
     * Квартальная премия
     */
    @JsonInclude(Include.NON_NULL)
    private Double quarterlyBonus;

    /***
     * Код ошибки
     */
    @JsonInclude(Include.NON_EMPTY)
    private ErrorCodes errorCode;

    /***
     * Сообщение об ошибке
     */
    @JsonInclude(Include.NON_EMPTY)
    private ErrorMessages errorMessage;

    public String toString() {
        return  "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", code='" + code + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                "}";
    }
}
