package ru.stepanovgzh.wct.orderingms.data.repository;

import org.springframework.data.jpa.repository.*;
import ru.stepanovgzh.wct.orderingms.data.entity.*;

import java.util.*;

public interface TransporterRepository extends JpaRepository<Transporter, UUID>
{
}
