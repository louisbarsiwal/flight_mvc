package flightmanagement.app.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;


@Service // Spring-managed bean
public class PermissionService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// Method to grant access and store it in the database
	public void grantPermissions(Integer flightManagerId) {
		String sql = "INSERT INTO access_control (flightManager_id, status) VALUES (?, 'GRANT') "
				+ "ON DUPLICATE KEY UPDATE status = 'GRANT'";
		jdbcTemplate.update(sql, flightManagerId);
	}

	// Method to revoke access and store it in the database
	public void revokePermissions(Integer flightManagerId) {
		String sql = "INSERT INTO access_control (flightManager_id, status) VALUES (?, 'REVOKE') "
				+ "ON DUPLICATE KEY UPDATE status = 'REVOKE'";
		jdbcTemplate.update(sql, flightManagerId);
	}


	// Using NamedParameterJdbcTemplate
	public boolean isRevoked(Integer flightManagerId) {
		String sql = "SELECT status FROM access_control WHERE flightManager_id = :flightManager_id";
		Map<String, Object> params = new HashMap<>();
		params.put("flightManager_id", flightManagerId);

		try {
			String status = namedParameterJdbcTemplate.queryForObject(sql, params, String.class);
			return "REVOKE".equals(status);
		} catch (EmptyResultDataAccessException e) {
			return false; // Default to false if no status found
		}
	}

}
