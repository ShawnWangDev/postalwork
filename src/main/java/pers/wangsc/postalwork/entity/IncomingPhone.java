package pers.wangsc.postalwork.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
public class IncomingPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 11)
    @NotBlank
    @Length(min=11,max = 11)
    private String phoneNo;
    @NotNull
    private Date callInTime;
    private Date createTime;
    @NotBlank
    private String detail;
    @OneToOne
    @JoinColumn(name="issue_type_id")
    @NotNull
    private IssueType issueType;
    @OneToOne
    @JoinColumn(name="express_brand_id")
    @NotNull
    private ExpressBrand expressBrand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public ExpressBrand getExpressBrand() {
        return expressBrand;
    }

    public void setExpressBrand(ExpressBrand expressBrand) {
        this.expressBrand = expressBrand;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCallInTime() {
        return callInTime;
    }

    public void setCallInTime(Date callInTime) {
        this.callInTime = callInTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "IncomingPhone{" +
                "id=" + id +
                ", phoneNo='" + phoneNo + '\'' +
                ", callInTime=" + callInTime +
                ", createTime=" + createTime +
                ", detail='" + detail + '\'' +
                ", issueType=" + issueType +
                ", expressBrand=" + expressBrand +
                '}';
    }
}
