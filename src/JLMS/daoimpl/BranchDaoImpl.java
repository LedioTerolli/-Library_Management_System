package JLMS.daoimpl;

import JLMS.DBConn;
import JLMS.dao.BranchDao;
import JLMS.model.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDaoImpl implements BranchDao {

    public BranchDaoImpl() {}

    @Override
    public List getAll() throws Exception {
        List<Branch> branchList = new ArrayList<>();
        ResultSet rs;
        Branch currentBranch;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM branch")
        ) {
            conn.setAutoCommit(false);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No data found!");
                return branchList;
            } else {
                while (rs.next()) {
                    currentBranch = extractBranch(rs);
                    branchList.add(currentBranch);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return branchList;    }

    @Override
    public Branch getByBranch_id(int branch_id) throws Exception {
        ResultSet rs;
        Branch foundBranch = null;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM BRANCH WHERE branch_id = ?")
        ) {
            conn.setAutoCommit(false);
            statement.setInt(1, branch_id);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
                return foundBranch;
            } else {
                rs.next();
                foundBranch = extractBranch(rs);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundBranch;
    }

    @Override
    public Branch getByMgr_id(int mgr_id) throws Exception {
        ResultSet rs;
        Branch foundBranch = null;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM BRANCH WHERE mgr_id = ?")
        ) {
            conn.setAutoCommit(false);
            statement.setInt(1, mgr_id);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
                return foundBranch;
            } else {
                rs.next();
                foundBranch = extractBranch(rs);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundBranch;
    }

    @Override
    public void add(Object objBranch) throws Exception {
        Branch branch = (Branch) objBranch;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO EMPLOYEE VALUES(?,?,?,?)")
        ) {
            conn.setAutoCommit(false);
            buildStatement(branch, statement);
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Object objBranch) throws Exception {
        Branch branch = (Branch) objBranch;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "UPDATE BRANCH SET " +
                             "BRANCH_ID = ?, " +
                             "BRANCH_NAME = ?, " +
                             "ADDRESS = ?, " +
                             "MGR_ID = ? " +
                             "WHERE BRANCH_ID = ? "
             )
        ) {
            conn.setAutoCommit(false);
            buildStatement(branch, statement);
            statement.setInt(5, branch.getBranch_id());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object objBranch) throws Exception {
        Branch branch = (Branch) objBranch;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("DELETE FROM BRANCH WHERE branch_id = ?")
        ) {
            statement.setInt(1, branch.getBranch_id());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //----------------------- HELPER METHODS -----------------------------------------------------

    private void buildStatement(Branch branch, PreparedStatement statement) throws SQLException {
        statement.setInt(1, branch.getBranch_id());
        statement.setString(2, branch.getBranch_name());
        statement.setString(3, branch.getAddress());
        statement.setInt(4, branch.getMgr_id());
    }

    private Branch extractBranch(ResultSet rs) throws SQLException {
        Branch branch = new Branch(
                rs.getInt("branch_id"),
                rs.getString("branch_name"),
                rs.getString("address"),
                rs.getInt("mgr_id")
        );
        return branch;
    }
}
