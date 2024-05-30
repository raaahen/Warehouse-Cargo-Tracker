package ru.stepanovgzh.wct.pickingms.data.repository;

import org.springframework.data.jpa.repository.*;
import ru.stepanovgzh.wct.pickingms.data.entity.*;

import java.util.*;

public interface TransporterRepository extends JpaRepository<Transporter, UUID>
{
}
