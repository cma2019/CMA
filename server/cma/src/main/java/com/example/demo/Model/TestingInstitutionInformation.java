package com.example.demo.Model;
import javax.persistence.*;
@Entity
@Table(name = "TII_tables")
public class TestingInstitutionInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String testingInstitutionName;     //检测检验机构名称
    private String testingInstitutionAddress;       //地址
    private String postcode;       //邮编
    private String fax;        //传真
    private String email;        //Email地址
    private String tiPeopleInCharge;         //负责人
    private String tiPicPosition;        //负责人职务
    private String tiPicTelephone;         //负责人固定电话
    private String tiPicMobilephone;         //负责人手机
    private String liaison;         //联络人
    private String liaisonPosition;        //联络人职务
    private String liaisonTelephone;         //联络人固定电话
    private String liaisonMobilephone;         //联络人手机
    private String socialCreditCode;       //社会信用代码

    private String legalEntityBelongedName;     //所属法人单位名称
    private String legalEntityBelongedAddress;       //地址
    private String lebPeopleInCharge;         //负责人
    private String lebPicPosition;        //负责人职务
    private String lebPicTelephone;         //负责人电话
    private String lebSocialCreditCode;       //社会信用代码

    private String competentDepartmentName;     //主管部门名称
    private String competentDepartmentAddress;       //地址
    private String cdPeopleInCharge;         //负责人
    private String cdPicPosition;        //负责人职务
    private String cdPicTelephone;         //负责人电话

    private Byte characteristic;        //检测机构设施特点(0固定，1临时，2可移动，3多场所）
    private Byte legalEntity;        //法人类别，独立法人检验检测机构（0社团法人，1事业法人，2企业法人，3机关法人，4其他）检验检测机构所属法人（非独立法人检验检测机构填写此项）（5社团法人，6事业法人，7企业法人，8机关法人，9其他）

    public void setTiPicPosition(String tiPicPosition) {
        this.tiPicPosition = tiPicPosition;
    }

    public String getTiPicPosition() {
        return tiPicPosition;
    }

    public void setTiPeopleInCharge(String tiPeopleInCharge) {
        this.tiPeopleInCharge = tiPeopleInCharge;
    }

    public void setTestingInstitutionName(String testingInstitutionName) {
        this.testingInstitutionName = testingInstitutionName;
    }

    public void setTestingInstitutionAddress(String testingInstitutionAddress) {
        this.testingInstitutionAddress = testingInstitutionAddress;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTiPeopleInCharge() {
        return tiPeopleInCharge;
    }

    public String getTestingInstitutionName() {
        return testingInstitutionName;
    }

    public String getTestingInstitutionAddress() {
        return testingInstitutionAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getTiPicTelephone() {
        return tiPicTelephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLebPicTelephone(String lebPicTelephone) {
        this.lebPicTelephone = lebPicTelephone;
    }

    public void setLebPicPosition(String lebPicPosition) {
        this.lebPicPosition = lebPicPosition;
    }

    public String getTiPicMobilephone() {
        return tiPicMobilephone;
    }

    public void setTiPicTelephone(String tiPicTelephone) {
        this.tiPicTelephone = tiPicTelephone;
    }

    public void setCdPicTelephone(String cdPicTelephone) {
        this.cdPicTelephone = cdPicTelephone;
    }

    public void setCdPicPosition(String cdPicPosition) {
        this.cdPicPosition = cdPicPosition;
    }

    public void setTiPicMobilephone(String tiPicMobilephone) {
        this.tiPicMobilephone = tiPicMobilephone;
    }

    public String getLebPicTelephone() {
        return lebPicTelephone;
    }

    public String getLebPicPosition() {
        return lebPicPosition;
    }

    public String getCdPicTelephone() {
        return cdPicTelephone;
    }

    public String getCdPicPosition() {
        return cdPicPosition;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public void setLiaisonTelephone(String liaisonTelephone) {
        this.liaisonTelephone = liaisonTelephone;
    }

    public void setLiaisonPosition(String liaisonPosition) {
        this.liaisonPosition = liaisonPosition;
    }

    public void setLiaisonMobilephone(String liaisonMobilephone) {
        this.liaisonMobilephone = liaisonMobilephone;
    }

    public void setLiaison(String liaison) {
        this.liaison = liaison;
    }

    public void setLegalEntityBelongedName(String legalEntityBelongedName) {
        this.legalEntityBelongedName = legalEntityBelongedName;
    }

    public void setLegalEntityBelongedAddress(String legalEntityBelongedAddress) {
        this.legalEntityBelongedAddress = legalEntityBelongedAddress;
    }

    public void setLegalEntity(Byte legalEntity) {
        this.legalEntity = legalEntity;
    }

    public void setLebSocialCreditCode(String lebSocialCreditCode) {
        this.lebSocialCreditCode = lebSocialCreditCode;
    }

    public void setLebPeopleInCharge(String lebPeopleInCharge) {
        this.lebPeopleInCharge = lebPeopleInCharge;
    }

    public void setCompetentDepartmentName(String competentDepartmentName) {
        this.competentDepartmentName = competentDepartmentName;
    }

    public void setCompetentDepartmentAddress(String competentDepartmentAddress) {
        this.competentDepartmentAddress = competentDepartmentAddress;
    }

    public void setCharacteristic(Byte characteristic) {
        this.characteristic = characteristic;
    }

    public void setCdPeopleInCharge(String cdPeopleInCharge) {
        this.cdPeopleInCharge = cdPeopleInCharge;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public String getLiaisonTelephone() {
        return liaisonTelephone;
    }

    public String getLiaisonPosition() {
        return liaisonPosition;
    }

    public String getLiaisonMobilephone() {
        return liaisonMobilephone;
    }

    public String getLiaison() {
        return liaison;
    }

    public String getLegalEntityBelongedName() {
        return legalEntityBelongedName;
    }

    public String getLegalEntityBelongedAddress() {
        return legalEntityBelongedAddress;
    }

    public String getLebSocialCreditCode() {
        return lebSocialCreditCode;
    }

    public String getLebPeopleInCharge() {
        return lebPeopleInCharge;
    }

    public String getCompetentDepartmentName() {
        return competentDepartmentName;
    }

    public String getCompetentDepartmentAddress() {
        return competentDepartmentAddress;
    }

    public String getCdPeopleInCharge() {
        return cdPeopleInCharge;
    }

    public Byte getLegalEntity() {
        return legalEntity;
    }

    public Byte getCharacteristic() {
        return characteristic;
    }
}



