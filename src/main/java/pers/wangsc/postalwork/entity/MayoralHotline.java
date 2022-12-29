package pers.wangsc.postalwork.entity;

import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity(name = "mayoral_hotline")
public class MayoralHotline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String complaintType;
    private String complainantName;
    private String complainantCalledPhoneNumber;
    private String complainantContactPhoneNumber;
    private String sourceOfAcceptance;
    private Date assignmentDateTime;
    private Date appealDateTime;
    private Date deadlineDateTime;
    private String complainantAddress;
    private String appealPurpose;
    private String appealDetail;
    private String reply;
    private String remark;
    private String satisfaction;

    private String additionalRemark;
    @OneToOne
    @JoinColumn(name = "express_brand_id", referencedColumnName = "id")
    private ExpressBrand expressBrand;
    @OneToOne
    @JoinColumn(name = "issue_type_id", referencedColumnName = "id")
    private IssueType issueType;
    @OneToOne
    @JoinColumn(name = "issue_condition_id", referencedColumnName = "id")
    private IssueCondition issueCondition;

    public MayoralHotline() {
    }

    public MayoralHotline(List<String> data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (data.size() == 14) {
            complaintType = data.get(0);
            complainantName = data.get(1);
            complainantCalledPhoneNumber = data.get(2);
            complainantContactPhoneNumber = data.get(3);
            sourceOfAcceptance = data.get(4);
            try {
                assignmentDateTime = dateFormat.parse(data.get(5));
                appealDateTime = dateFormat.parse(data.get(6));
                deadlineDateTime = dateFormat.parse(data.get(7));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            complainantAddress = data.get(8);
            appealPurpose = data.get(9);
            appealDetail = data.get(10);
            reply = data.get(11);
            remark = data.get(12);
            satisfaction = data.get(13);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getComplainantName() {
        return complainantName;
    }

    public void setComplainantName(String complainantName) {
        this.complainantName = complainantName;
    }

    public String getComplainantCalledPhoneNumber() {
        return complainantCalledPhoneNumber;
    }

    public void setComplainantCalledPhoneNumber(String complainantCalledPhoneNumber) {
        this.complainantCalledPhoneNumber = complainantCalledPhoneNumber;
    }

    public String getComplainantContactPhoneNumber() {
        return complainantContactPhoneNumber;
    }

    public void setComplainantContactPhoneNumber(String complainantContactPhoneNumber) {
        this.complainantContactPhoneNumber = complainantContactPhoneNumber;
    }

    public String getSourceOfAcceptance() {
        return sourceOfAcceptance;
    }

    public void setSourceOfAcceptance(String sourceOfAcceptance) {
        this.sourceOfAcceptance = sourceOfAcceptance;
    }

    public Date getAssignmentDateTime() {
        return assignmentDateTime;
    }

    public void setAssignmentDateTime(Date assignmentDateTime) {
        this.assignmentDateTime = assignmentDateTime;
    }

    public Date getAppealDateTime() {
        return appealDateTime;
    }

    public void setAppealDateTime(Date appealDateTime) {
        this.appealDateTime = appealDateTime;
    }

    public Date getDeadlineDateTime() {
        return deadlineDateTime;
    }

    public void setDeadlineDateTime(Date deadlineDateTime) {
        this.deadlineDateTime = deadlineDateTime;
    }

    public String getComplainantAddress() {
        return complainantAddress;
    }

    public void setComplainantAddress(String complainantAddress) {
        this.complainantAddress = complainantAddress;
    }

    public String getAppealPurpose() {
        return appealPurpose;
    }

    public void setAppealPurpose(String appealPurpose) {
        this.appealPurpose = appealPurpose;
    }

    public String getAppealDetail() {
        return appealDetail;
    }

    public void setAppealDetail(String appealDetail) {
        this.appealDetail = appealDetail;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getAdditionalRemark() {
        return additionalRemark;
    }

    public void setAdditionalRemark(String additionalRemark) {
        this.additionalRemark = additionalRemark;
    }

    public ExpressBrand getExpressBrand() {
        return expressBrand;
    }

    public void setExpressBrand(ExpressBrand expressBrand) {
        this.expressBrand = expressBrand;
    }

    public void setExpressBrand(List<ExpressBrand> expressBrandList) {
        for (var express : expressBrandList) {
            String expressName = express.getName();
            if (appealDetail.contains(expressName) || appealPurpose.contains(expressName)) {
                expressBrand=express;
            }
        }
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public IssueCondition getIssueCondition() {
        return issueCondition;
    }

    public void setIssueCondition(IssueCondition issueCondition) {
        this.issueCondition = issueCondition;
    }

    @Override
    public String toString() {
        return "MayoralHotline{" +
                "id=" + id +
                ", complaintType='" + complaintType + '\'' +
                ", complainantName='" + complainantName + '\'' +
                ", complainantCalledPhoneNumber='" + complainantCalledPhoneNumber + '\'' +
                ", complainantContactPhoneNumber='" + complainantContactPhoneNumber + '\'' +
                ", sourceOfAcceptance='" + sourceOfAcceptance + '\'' +
                ", assignmentDateTime=" + assignmentDateTime +
                ", appealDateTime=" + appealDateTime +
                ", deadlineDateTime=" + deadlineDateTime +
                ", complainantAddress='" + complainantAddress + '\'' +
                ", appealPurpose='" + appealPurpose + '\'' +
                ", appealDetail='" + appealDetail + '\'' +
                ", reply='" + reply + '\'' +
                ", remark='" + remark + '\'' +
                ", satisfaction='" + satisfaction + '\'' +
                ", additionalRemark='" + additionalRemark + '\'' +
                ", expressBrand=" + expressBrand +
                ", issueType=" + issueType +
                ", issueCondition=" + issueCondition +
                '}';
    }
}
