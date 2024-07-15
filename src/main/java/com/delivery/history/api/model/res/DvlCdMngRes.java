package com.delivery.history.api.model.res;

import lombok.Data;

/**
 * <p>
 * 배송비 정책 관리를 위한 응답 데이타
 * </p>
 *
 * @author gyeongwooPark
 * @version 1.0
 * @since 2024.07
 */
@Data
public class DvlCdMngRes {
    // 배송정책코드
    private String dlvCd;
    // 배송정책유형코드
    private String dlvTypeCd;
    // 배송 정책 이름
    private String dlvName;
    // 적용 날짜
    private String startDate;
    // 종료 날짜
    private String endDate;
    // 배송비
    private String dvlCost;
    // 사용여부
    private String useYn;
}
