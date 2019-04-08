package com.zwg.javabase.dynamicproxy;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 张文刚
 * @Date: 2019/02/18  15:45
 * @Version: V1.0
 * @Description:
 */
public class AA implements Serializable {

    @Override
    public String toString() {
        return "AA{" +
                "prescriptionId=" + prescriptionId +
                ", cgCode=" + cgCode +
                ", cpCode=" + cpCode +
                ", busiCode=" + busiCode +
                ", customer=" + customer +
                ", firstPrescriptionPic='" + firstPrescriptionPic + '\'' +
                ", secondPrescriptionPic='" + secondPrescriptionPic + '\'' +
                ", thirdPrescriptionPic='" + thirdPrescriptionPic + '\'' +
                ", fourthPrescriptionPic='" + fourthPrescriptionPic + '\'' +
                ", fifthPrescriptionPic='" + fifthPrescriptionPic + '\'' +
                '}';
    }

    public AA(long prescriptionId, int cgCode, int cpCode, int busiCode) {
        this.prescriptionId = prescriptionId;
        this.cgCode = cgCode;
        this.cpCode = cpCode;
        this.busiCode = busiCode;
    }

    public AA() {
    }

    private static final long serialVersionUID = 6323259329108719004L;
    /**
     * 处方id
     */
    private long prescriptionId;

    /**
     * 集团编码
     */

    private int cgCode;

    /**
     * 企业编码
     */
    private int cpCode;

    /**
     * 业务机构编码
     */
    private int busiCode;
    /**
     * 顾客信息VO
     */
    private CustomerVO customer;

    /**
     * 处方照片1
     */
    private String firstPrescriptionPic;

    /**
     * 处方照片2
     */
    private String secondPrescriptionPic;

    /**
     * 处方照片3
     */
    private String thirdPrescriptionPic;

    /**
     * 处方照片4
     */
    private String fourthPrescriptionPic;

    /**
     * 处方照片5
     */
    private String fifthPrescriptionPic;

    public long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getCgCode() {
        return cgCode;
    }

    public void setCgCode(int cgCode) {
        this.cgCode = cgCode;
    }

    public int getCpCode() {
        return cpCode;
    }

    public void setCpCode(int cpCode) {
        this.cpCode = cpCode;
    }

    public int getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(int busiCode) {
        this.busiCode = busiCode;
    }

    public CustomerVO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVO customer) {
        this.customer = customer;
    }

    public String getFirstPrescriptionPic() {
        return firstPrescriptionPic;
    }

    public void setFirstPrescriptionPic(String firstPrescriptionPic) {
        this.firstPrescriptionPic = firstPrescriptionPic;
    }

    public String getSecondPrescriptionPic() {
        return secondPrescriptionPic;
    }

    public void setSecondPrescriptionPic(String secondPrescriptionPic) {
        this.secondPrescriptionPic = secondPrescriptionPic;
    }

    public String getThirdPrescriptionPic() {
        return thirdPrescriptionPic;
    }

    public void setThirdPrescriptionPic(String thirdPrescriptionPic) {
        this.thirdPrescriptionPic = thirdPrescriptionPic;
    }

    public String getFourthPrescriptionPic() {
        return fourthPrescriptionPic;
    }

    public void setFourthPrescriptionPic(String fourthPrescriptionPic) {
        this.fourthPrescriptionPic = fourthPrescriptionPic;
    }

    public String getFifthPrescriptionPic() {
        return fifthPrescriptionPic;
    }

    public void setFifthPrescriptionPic(String fifthPrescriptionPic) {
        this.fifthPrescriptionPic = fifthPrescriptionPic;
    }

    public static class CustomerVO implements Serializable {
        @Override
        public String toString() {
            return "CustomerVO{" +
                    "prescriptionId=" + prescriptionId +
                    ", cgCode=" + cgCode +
                    ", cpCode=" + cpCode +
                    ", busiCode=" + busiCode +
                    ", name='" + name + '\'' +
                    ", gender=" + gender +
                    ", idcard='" + idcard + '\'' +
                    ", brithday=" + brithday +
                    ", phone='" + phone + '\'' +
                    ", address='" + address + '\'' +
                    ", nation='" + nation + '\'' +
                    ", prescriptionDoctorId=" + prescriptionDoctorId +
                    ", prescriptionDoctorName='" + prescriptionDoctorName + '\'' +
                    ", hospitalName='" + hospitalName + '\'' +
                    ", department='" + department + '\'' +
                    ", diagnosisContent='" + diagnosisContent + '\'' +
                    '}';
        }

        private static final long serialVersionUID = 8859381188664991105L;

        /**
         * 处方编号
         */
        private long prescriptionId;

        /**
         * 集团编码
         */
        private int cgCode;

        /**
         * 企业编码
         */
        private int cpCode;

        /**
         * 业务机构编码
         */
        private int busiCode;

        /**
         * 顾客姓名
         */
        private String name;

        /**
         * 顾客性别
         */
        private int gender;

        /**
         * 身份证号
         */
        private String idcard;
        /**
         * 出生日期
         */
        @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
        private Date brithday;


        /**
         * 联系电话
         */
        private String phone;

        /**
         * 家庭住址
         */
        private String address;

        /**
         * 民族
         */
        private String nation;

        /**
         * 开处方医师id
         */
        private long prescriptionDoctorId;

        /**
         * 开处方医师名称
         */
        private String prescriptionDoctorName;

        /**
         * 医院名称
         */
        private String hospitalName;

        /**
         * 科别
         */
        private String department;

        /**
         * 诊断内容
         */
        private String diagnosisContent;


        public String getDiagnosisContent() {
            return diagnosisContent;
        }

        public void setDiagnosisContent(String diagnosisContent) {
            this.diagnosisContent = diagnosisContent;
        }


        public long getPrescriptionId() {
            return prescriptionId;
        }

        public void setPrescriptionId(long prescriptionId) {
            this.prescriptionId = prescriptionId;
        }

        public int getCgCode() {
            return cgCode;
        }

        public void setCgCode(int cgCode) {
            this.cgCode = cgCode;
        }

        public int getCpCode() {
            return cpCode;
        }

        public void setCpCode(int cpCode) {
            this.cpCode = cpCode;
        }

        public int getBusiCode() {
            return busiCode;
        }

        public void setBusiCode(int busiCode) {
            this.busiCode = busiCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public Date getBrithday() {
            return brithday;
        }

        public void setBrithday(Date brithday) {
            this.brithday = brithday;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public long getPrescriptionDoctorId() {
            return prescriptionDoctorId;
        }

        public void setPrescriptionDoctorId(long prescriptionDoctorId) {
            this.prescriptionDoctorId = prescriptionDoctorId;
        }

        public String getPrescriptionDoctorName() {
            return prescriptionDoctorName;
        }

        public void setPrescriptionDoctorName(String prescriptionDoctorName) {
            this.prescriptionDoctorName = prescriptionDoctorName;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }
    }


}

