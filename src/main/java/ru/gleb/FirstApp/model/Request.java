package ru.gleb.FirstApp.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gleb.FirstApp.constraint.UniqueId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    /***
     * Уникальный идентификатор сообщения
     */
    @NotBlank(message = "UID не может быть пустым")
    @Size(max = 32, message = "Длина UID не может быть более 32 символов")
    @UniqueId(message = "UID не может быть равен '123'")
    private String uid;

    /***
     * Уникальный идентификатор операции
     */
    @NotBlank(message = "UID операции не может быть пустым")
    @Size(max = 32, message = "Длина UID операции не может быть более 32 символов")
    private String operationUid;

    /***
     * Имя системы отправителя
     */
    private Systems systemName;

    /***
     * Время создания сообщения
     */
    @NotBlank(message = "Метка времени не может быть пустой")
    private String systemTime;

    /***
     * Наименование ресурса
     */
    private String source;

    /***
     * Должность
     */
    private Positions position;

    /***
     * Оклад
     */
    private int salary;

    /***
     * Премиальный коэффициент
     */
    private float bonus;

    /***
     * Отработанное время
     */
    private int workDays;

    /***
     * Уникальный идентификатор коммуникации
     */
    @Min(value = 1, message = "Id коммуникации не может быть менее 1")
    @Max(value = 100000, message = "Id коммуникации не может быть более 100000")
    private int communicationId;

    /***
     * Уникальный идентификатор шаблона
     */
    private int templateId;

    /***
     * Код продукта
     */
    private int productCode;

    /***
     * SMS-код
     */
    private int smsCode;

    @Override
    public String toString() {
        return  "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId='" + communicationId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", smsCode='" + smsCode + '\'' +
                "}";
    }
}
