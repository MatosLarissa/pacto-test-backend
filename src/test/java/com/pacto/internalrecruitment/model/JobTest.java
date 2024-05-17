package com.pacto.internalrecruitment.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    void testOnCreate() {
        Job job = new Job();
        assertNull(job.getCreationDate());
        assertNull(job.getStatus());

        job.onCreate();

        assertNotNull(job.getCreationDate());
        assertEquals("OPEN", job.getStatus());
    }

    @Test
    void testOnUpdate() {
        Job job = new Job();
        assertNull(job.getLastUpdate());

        job.onUpdate();

        assertNotNull(job.getLastUpdate());
    }

    @Test
    void testGetterAndSetters() {
        Job job = new Job();
        job.setJobId(1);
        job.setTitle("Software Engineer");
        job.setDescription("Develop software applications");
        job.setStatus("OPEN");
        job.setCreatorId(123);
        LocalDateTime now = LocalDateTime.now();
        job.setCreationDate(now);
        job.setLastUpdate(now);

        assertEquals(1, job.getJobId());
        assertEquals("Software Engineer", job.getTitle());
        assertEquals("Develop software applications", job.getDescription());
        assertEquals("OPEN", job.getStatus());
        assertEquals(123, job.getCreatorId());
        assertEquals(now, job.getCreationDate());
        assertEquals(now, job.getLastUpdate());
    }

    @Test
    void testRequirements() {
        Job job = new Job();
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setRequirementName("Java");

        job.getRequirements().add(requirement);

        assertFalse(job.getRequirements().isEmpty());
        assertTrue(job.getRequirements().contains(requirement));
    }
}
