package JLMS.model;

public class Branch {

    private int branch_id;
    private String branch_name;
    private String address;
    private int mgr_id;

    public Branch() {
    }

    public Branch(int branch_id, String branch_name, String address, int mgr_id) {
        this.branch_id = branch_id;
        this.branch_name = branch_name;
        this.address = address;
        this.mgr_id = mgr_id;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMgr_id() {
        return mgr_id;
    }

    public void setMgr_id(int mgr_id) {
        this.mgr_id = mgr_id;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branch_id=" + branch_id +
                ", branch_name='" + branch_name + '\'' +
                ", address='" + address + '\'' +
                ", mgr_id=" + mgr_id +
                '}';
    }
}
