package JLMS.dao;

import JLMS.model.Book;
import JLMS.model.Branch;

import java.util.List;

public interface BranchDao extends GenericDao {
    Branch getByBranch_id(int branch_id) throws Exception;

    Branch getByMgr_id(int mgr_id) throws Exception;
}
