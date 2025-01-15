package k35.domain;

import k35.domain.interfaces.UniqueIntId;
import k35.domain.interfaces.UniqueLongId;

import java.util.List;

public final class DomainTools {

    private DomainTools() {
    }

    /**
     * Получить списко идентификаторов
     *
     * @param objects
     * @return
     */
    public static long[] getLongIds(List<UniqueLongId> objects) {
        long[] array = new long[objects.size()];

        int i = 0;
        for (final var obj : objects) {
            array[i++] = obj.getId();
        }

        return array;
    }

    /**
     * Получить списко идентификаторов
     *
     * @param objects
     * @return
     */
    public static int[] getIntIds(List<UniqueIntId> objects) {
        int[] array = new int[objects.size()];

        int i = 0;
        for (final var obj : objects) {
            array[i++] = obj.getId();
        }

        return array;
    }

}
