package pers.wangsc.postalwork.util.mayoralhotlineissue;

import pers.wangsc.postalwork.entity.MayoralHotlineIssue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatisticStrategy {
    private enum StatisticStrategyConditionType {
        TO_BE_DONE, VALID, INVALID
    }

    public static final Integer CONDITION_UNSET = 1;
    public static final Integer CONDITION_INFORMED = 2;
    public static final Integer CONDITION_NEED_TO_VERIFY = 3;
    public static final Integer CONDITION_AGREE = 4;
    public static final Integer CONDITION_DISAGREE = 5;
    public static final Integer CONDITION_DEALING = 6;
    public static final Integer CONDITION_EPIDEMICS = 7;
    public static final Integer CONDITION_NO_RESPONSE_BEFORE_DEADLINE = 10;

    // invalid
    public static final Integer CONDITION_CONTACT_SENDER = 8;
    public static final Integer CONDITION_SOLVED_BEFORE_INFORM_COMPANY = 9;
    public static final Integer CONDITION_TRANSFERRED = 11;
    public static final Integer CONDITION_CONTACT_SHIPPER = 12;
    public static final Integer CONDITION_NO_RESPONSIBILITY = 13;
    public static final Integer CONDITION_COMPLAINANT_CANCELED = 14;

    private List<Integer> toBeDoneIdList;
    private List<Integer> validIdList;
    private List<Integer> invalidIdList;

    private List<MayoralHotlineIssue> toBeDoneList;
    private List<MayoralHotlineIssue> validList;
    private List<MayoralHotlineIssue> invalidList;

    public StatisticStrategy() {
        toBeDoneIdList = new ArrayList<>();
        validIdList = new ArrayList<>();
        invalidIdList = new ArrayList<>();
        Collections.addAll(toBeDoneIdList, CONDITION_UNSET, CONDITION_INFORMED, CONDITION_NEED_TO_VERIFY
                , CONDITION_DEALING, CONDITION_NO_RESPONSE_BEFORE_DEADLINE);
        Collections.addAll(validIdList, CONDITION_AGREE, CONDITION_DISAGREE, CONDITION_EPIDEMICS);
        Collections.addAll(invalidIdList, CONDITION_CONTACT_SENDER, CONDITION_SOLVED_BEFORE_INFORM_COMPANY
                , CONDITION_TRANSFERRED, CONDITION_CONTACT_SHIPPER, CONDITION_NO_RESPONSIBILITY,
                CONDITION_COMPLAINANT_CANCELED);
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

    private StatisticStrategyConditionType judgeCondition(Integer id) {
        if (toBeDoneIdList.contains(id)) {
            return StatisticStrategyConditionType.TO_BE_DONE;
        } else if (validIdList.contains(id)) {
            return StatisticStrategyConditionType.VALID;
        }
        return StatisticStrategyConditionType.INVALID;
    }

    public List<Integer> getToBeDoneIdList() {
        return toBeDoneIdList;
    }

    public List<Integer> getValidIdList() {
        return validIdList;
    }

    public List<Integer> getInvalidIdList() {
        return invalidIdList;
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
