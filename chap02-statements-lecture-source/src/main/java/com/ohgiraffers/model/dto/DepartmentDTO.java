package com.ohgiraffers.model.dto;

public class DepartmentDTO {

    private String deptId;
    private String deptTitle;
    private String locationId;

    public DepartmentDTO() {
    }

    public DepartmentDTO(String deptId, String deptTitle, String locationId) {
        this.deptId = deptId;
        this.deptTitle = deptTitle;
        this.locationId = locationId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptCode) {
        this.deptId = deptCode;
    }

    public String getDeptTitle() {
        return deptTitle;
    }

    public void setDeptTitle(String deptTitle) {
        this.deptTitle = deptTitle;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "deptCode='" + deptId + '\'' +
                ", deptTitle='" + deptTitle + '\'' +
                ", locationId='" + locationId + '\'' +
                '}';
    }
}