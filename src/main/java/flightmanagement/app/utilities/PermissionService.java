package flightmanagement.app.utilities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // Grant Permissions
    public void grantPermissions(Integer flightManagerId) throws Exception {
        try {
            String sql = "UPDATE access_control SET status = 'GRANT' WHERE flightManager_id = ?";
            jdbcTemplate.update(sql, flightManagerId); // Execute update query
        } catch (Exception e) {
            throw new Exception("Failed to grant access.", e);
        }
    }

    // Revoke Permissions
    public void revokePermissions(Integer flightManagerId) throws Exception {
        try {
            String sql = "UPDATE access_control SET status = 'REVOKE' WHERE flightManager_id = ?";
            jdbcTemplate.update(sql, flightManagerId); // Execute update query
        } catch (Exception e) {
            throw new Exception("Failed to revoke access.", e);
        }
    }

    public boolean isRevoked(Integer flightManagerId) {
        String sql = "SELECT status FROM access_control WHERE flightManager_id = :flightManagerId";

        Map<String, Object> params = new HashMap<>();
        params.put("flightManagerId", flightManagerId);

        String accessStatus = namedParameterJdbcTemplate.queryForObject(sql, params, String.class);

        // Return true if status is "revoke" (indicating access is revoked)
        return "REVOKE".equals(accessStatus);
    }
}
