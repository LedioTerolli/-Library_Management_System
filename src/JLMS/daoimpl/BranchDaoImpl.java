package JLMS.daoimpl;

import JLMS.dao.BranchDao;
import JLMS.model.Branch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BranchDaoImpl implements BranchDao {

    public BranchDaoImpl() {
    }

    @Override
    public List getAll() throws Exception {
        return null;
    }
    
    @Override
    public Branch getByBranch_id(int branch_id) throws Exception {
        return null;
    }

    @Override
    public Branch getByMgr_id(int mgr_id) throws Exception {
        return null;
    }

    @Override
    public void add(Object o) throws Exception {

    }

    @Override
    public void update(Object o) throws Exception {

    }

    @Override
    public void delete(Object o) throws Exception {

    }

    //----------------------- HELPER METHODS -----------------------------------------------------

    private void buildStatement(Branch branch, PreparedStatement statement) throws SQLException {
        statement.setInt(1, branch.getBranch_id());
        statement.setString(2, branch.getBranch_name());
        statement.setString(3, branch.getAddress());
        statement.setInt(4, branch.getMgr_id());
    }

    private Branch extractEmployee(ResultSet rs) throws SQLException {
        Branch branch = new Branch(
                rs.getInt("branch_id"),
                rs.getString("branch_name"),
                rs.getString("address"),
                rs.getInt("mgr_id")
        );
        return branch;
    }
}
