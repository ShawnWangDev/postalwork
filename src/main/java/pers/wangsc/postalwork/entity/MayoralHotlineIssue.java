package pers.wangsc.postalwork.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class MayoralHotlineIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MayoralHotline mayoralHotline;
    @JsonProperty
    @OneToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ExpressBrand expressBrand;
    @OneToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private IssueType issueType;
    @OneToOne
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private IssueCondition issueCondition;
    private Date updateDateTime;
    @Column(length = 1024)
    private String additionalRemark;

    public MayoralHotlineIssue() {
    }

    public MayoralHotlineIssue(Integer hotlineId) {
        mayoralHotline = new MayoralHotline();
        mayoralHotline.setId(hotlineId);
        setMayoralHotline(mayoralHotline);
        setExpressBrand(new ExpressBrand(1));
        setIssueType(new IssueType(1));
        setIssueCondition(new IssueCondition(1));
        setAdditionalRemark("");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MayoralHotline getMayoralHotline() {
        return mayoralHotline;
    }

    public void setMayoralHotline(MayoralHotline mayoralHotline) {
        this.mayoralHotline = mayoralHotline;
    }

    public ExpressBrand getExpressBrand() {
        return expressBrand;
    }

    public void setExpressBrand(ExpressBrand expressBrand) {
        this.expressBrand = expressBrand;
    }

    @JsonIgnore
    public void setExpressBrand(List<ExpressBrand> expressBrandList, Integer multipleExpressBrandId) {
        List<ExpressBrand> hotlineContainsExpressBrandList = new ArrayList<>();
        for (var express : expressBrandList) {
            String expressName = express.getName();
            if (mayoralHotline.getAppealDetail().contains(expressName) || mayoralHotline.getAppealPurpose().contains(expressName)) {
                hotlineContainsExpressBrandList.add(express);
            }
        }
        int size = hotlineContainsExpressBrandList.size();
        if (size < 1) {
            setExpressBrand(new ExpressBrand(1));
        } else if (size > 1) {
            setExpressBrand(new ExpressBrand(multipleExpressBrandId));
        } else {
            setExpressBrand(new ExpressBrand(hotlineContainsExpressBrandList.get(0).getId()));
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

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getAdditionalRemark() {
        return additionalRemark;
    }

    public void setAdditionalRemark(String additionalRemark) {
        this.additionalRemark = additionalRemark;
    }

    @Override
    public String toString() {
        return "MayoralHotlineIssue{" +
                "id=" + id +
                ", expressBrand=" + expressBrand +
                ", issueType=" + issueType +
                ", issueCondition=" + issueCondition +
                ", mayoralHotline=" + mayoralHotline +
                ", updateDateTime=" + updateDateTime +
                ", additionalRemark='" + additionalRemark + '\'' +
                '}';
    }
}
