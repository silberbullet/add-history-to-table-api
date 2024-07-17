package com.delivery.history.api.model.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 배송비 정책 관리를 위한 요청 데이타
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
@Data
@Accessors(chain = true)
public class DvlCdMngReq {

    // 배송정책코드
    private String dvlCd;
    // 배송정책유형코드
    @NotNull
    private String dvlTypeCd;
    // 배송 정책 이름
    private String dvlName;
    // 적용 날짜
    private String startDate;
    // 종료 날짜
    private String endDate;
    // 배송비
    private String dvlCost;
    // 사용여부
    private String useYn;
}