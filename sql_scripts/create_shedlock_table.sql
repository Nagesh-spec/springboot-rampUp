-- ShedLock table creation for Oracle database
-- This table is required for ShedLock to coordinate locks between multiple application instances

CREATE TABLE shedlock (
    name        VARCHAR2(64) NOT NULL,
    lock_until  TIMESTAMP(3) NOT NULL,
    locked_at   TIMESTAMP(3) DEFAULT SYSTIMESTAMP NOT NULL,
    locked_by   VARCHAR2(255) NOT NULL,
    CONSTRAINT pk_shedlock PRIMARY KEY (name)
);


-- Create an index for better performance
CREATE INDEX idx_shedlock_lock_until ON shedlock(lock_until);

-- Example of how to manually check locks (for debugging)
-- SELECT * FROM shedlock WHERE lock_until > CURRENT_TIMESTAMP;

-- Example of how to manually release a stuck lock (use with caution)
-- DELETE FROM shedlock WHERE name = 'yourLockName';    