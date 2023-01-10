package pers.wangsc.postalwork.util.mayoralhotlineissue;

import pers.wangsc.postalwork.entity.MayoralHotlineIssue;

import java.util.ArrayList;
import java.util.List;

public class StatisticStrategy {
    public enum StatisticStrategyConditionType {
        TO_BE_DONE, VALID, INVALID
    }

    private static final Integer CONDITION_UNSET = 1;
    private static final Integer CONDITION_INFORMED = 2;
    private static final Integer CONDITION_NEED_TO_VERIFY = 3;
    private static final Integer CONDITION_AGREE = 4;
    private static final Integer CONDITION_DISAGREE = 5;
    private static final Integer CONDITION_DEALING = 6;
    private static final Integer CONDITION_EPIDEMICS = 7;
    private static final Integer CONDITION_NO_RESPONSE_BEFORE_DEADLINE = 10;

    // invalid
    private static final Integer CONDITION_CONTACT_SENDER_ID = 8;
    private static final Integer CONDITION_SOLVED_BEFORE_INFORM_COMPANY_ID = 9;
    private static final Integer CONDITION_TRANSFERRED_ID = 11;
    private static final Integer CONDITION_CONTACT_SHIPPER_ID = 12;
    private static final Integer CONDITION_NO_RESPONSIBILITY_ID = 13;
    private static final Integer CONDITION_COMPLAINANT_CANCELED_ID = 14;

    private static StatisticStrategyConditionType judgeCondition(Integer id) {
        if (id == CONDITION_UNSET || id == CONDITION_INFORMED || id == CONDITION_NEED_TO_VERIFY
                || id == CONDITION_DEALING || id == CONDITION_NO_RESPONSE_BEFORE_DEADLINE) {
            return StatisticStrategyConditionType.TO_BE_DONE;
        } else if (id == CONDITION_AGREE || id == CONDITION_DISAGREE || id == CONDITION_EPIDEMICS) {
            return StatisticStrategyConditionType.VALID;
        }
        return StatisticStrategyConditionType.INVALID;
    }

    private List<MayoralHotlineIssue> toBeDoneList;
    private List<MayoralHotlineIssue> validList;
    private List<MayoralHotlineIssue> invalidList;

    public StatisticStrategy() {
    }

    public StatisticStrategy(List<MayoralHotlineIssue> list) {
        invalidList = new ArrayList<>();
        validList = new ArrayList<>();
        toBeDoneList = new ArrayList<>();
        for (MayoralHotlineIssue issue : list) {
            StatisticStrategyConditionType conditionType = judgeCondition(issue.getIssueCondition().getId());
            switch (conditionType) {
                case VALID -> validList.add(issue);
                case INVALID -> invalidList.add(issue);
                case TO_BE_DONE -> toBeDoneList.add(issue);
            }
        }
    }

    public List<MayoralHotlineIssue> getToBeDoneList() {
        return toBeDoneList;
    }

    public List<MayoralHotlineIssue> getValidList() {
        return validList;
    }

    public List<MayoralHotlineIssue> getInvalidList() {
        return invalidList;
    }
}
