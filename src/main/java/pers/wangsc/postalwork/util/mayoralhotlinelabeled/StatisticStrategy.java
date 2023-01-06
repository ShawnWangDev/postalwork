package pers.wangsc.postalwork.util.mayoralhotlinelabeled;

import pers.wangsc.postalwork.entity.MayoralHotlineLabeled;

import java.util.ArrayList;
import java.util.List;

public class StatisticStrategy {
    private static final String CONDITION_TYPE_UNDONE = "undone";
    private static final String CONDITION_TYPE_VALID = "valid";
    private static final String CONDITION_TYPE_INVALID = "invalid";
    // to be
    private static final Integer CONDITION_UNSET_SET_ID = 1;
    private static final Integer CONDITION_UNSET_INFORM = 2;
    private static final Integer CONDITION_UNSET_VERIFY = 3;
    private static final Integer CONDITION_UNSET_DEAL = 6;
    private static final Integer CONDITION_UNSET_RESPONSE_BY_COMPANY = 10;
    // valid
    private static final Integer CONDITION_VALID_AGREE = 4;
    private static final Integer CONDITION_VALID_DISAGREE = 5;
    private static final Integer CONDITION_VALID_EPIDEMICS = 7;
    // invalid
    private static final Integer CONDITION_INVALID_CONTACT_SENDER_ID = 8;
    private static final Integer CONDITION_INVALID_SOLVED_BEFORE_INFORM_COMPANY_ID = 9;
    private static final Integer CONDITION_INVALID_TRANSFERRED_ID = 11;
    private static final Integer CONDITION_INVALID_CONTACT_SHIPPER_ID = 12;
    private static final Integer CONDITION_INVALID_NO_RESPONSIBILITY_ID = 13;
    private static final Integer CONDITION_INVALID_COMPLAINANT_CANCELED_ID = 14;

    private static String judgeCondition(Integer id) {
        if (id == CONDITION_UNSET_SET_ID || id == CONDITION_UNSET_INFORM || id == CONDITION_UNSET_VERIFY
                || id == CONDITION_UNSET_DEAL || id == CONDITION_UNSET_RESPONSE_BY_COMPANY) {
            return CONDITION_TYPE_UNDONE;
        } else if (id == CONDITION_VALID_AGREE || id == CONDITION_VALID_DISAGREE || id == CONDITION_VALID_EPIDEMICS) {
            return CONDITION_TYPE_VALID;
        }
        return CONDITION_TYPE_INVALID;
    }

    private List<MayoralHotlineLabeled> unsetList;
    private List<MayoralHotlineLabeled> validList;
    private List<MayoralHotlineLabeled> invalidList;

    public StatisticStrategy() {
    }

    public StatisticStrategy(List<MayoralHotlineLabeled> list) {
        invalidList=new ArrayList<>();
        validList=new ArrayList<>();
        unsetList=new ArrayList<>();
        for (MayoralHotlineLabeled labeled : list) {
            String conditionType = judgeCondition(labeled.getIssueCondition().getId());
            switch (conditionType){
                case CONDITION_TYPE_VALID -> validList.add(labeled);
                case CONDITION_TYPE_INVALID -> invalidList.add(labeled);
                case CONDITION_TYPE_UNDONE -> unsetList.add(labeled);
            }
        }
    }

    public List<MayoralHotlineLabeled> getUnsetList() {
        return unsetList;
    }

    public List<MayoralHotlineLabeled> getValidList() {
        return validList;
    }

    public List<MayoralHotlineLabeled> getInvalidList() {
        return invalidList;
    }
}
