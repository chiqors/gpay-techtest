package com.dml.midapp.common.enums;

import lombok.Getter;

@Getter
public enum ResponseCodeEnum {
    OK(200, "Sukses"),
    OK_PARTIAL(206, "Success, partial content"),
    BAD_REQUEST(400, "Tolong cek kembali data yang dimasukkan"),
    UNAUTHORIZED(401, "Anda tidak diperbolehkan mengakses menu ini"),
    FORBIDDEN(403, "Anda tidak memiliki akses ke menu ini"),
    NOT_FOUND(404, "Data tidak ditemukan"),
    CREATED(200, "Sukses, data berhasil ditambahkan"),
    UPDATED(200, "Sukses, data berhasil diperbaharui"),
    DELETED(200, "Data berhasil dihapus"),
    REQUEST_ENTITY_TOO_LARGE(413, "Ukuran file terlalu besar"),
    INVALID_FILE_TYPE(415, "Tipe file tidak didukung"),
    ODOO_ERROR(503, "Maaf, terjadi kesalahan pada server Odoo"),
    XENDIT_CLIENT_ERROR(400, "Tolong cek kembali data yang dimasukkan"),
    XENDIT_SERVER_ERROR(503, "Maaf, terjadi kesalahan pada server Xendit"),
    INTERNAL_SERVER_ERROR(500, "Maaf, terjadi kesalahan pada server"),
    ;

    private int code;
    private String message;

    private ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseCodeEnum fromCode(int code) {
        ResponseCodeEnum result = null;
        for (ResponseCodeEnum responseCodeEnum : ResponseCodeEnum.values()) {
            if (responseCodeEnum.getCode() == code) {
                result = responseCodeEnum;
                break;
            }
        }
        return result;
    }
}
