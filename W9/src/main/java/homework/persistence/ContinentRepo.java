package homework.persistence;

import homework.model.Continents;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ContinentRepo extends AbstractRepo<Continents>
{
    public ContinentRepo()
    {
        super(Continents.class);
    }
}
