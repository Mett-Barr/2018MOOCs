package cycu.nclab.moocs2018.room;

import android.database.Cursor;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;
import java.util.Calendar;
import java.util.List;

@Dao
public interface MoneyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(MoneyEntity item);

    /** 根據id，更新資料 */
    /** It uses a query that matches against the primary key of each entity.*/
    @Update
    public void update(MoneyEntity item);
    /** 根據id，刪除資料 */
    /** It uses the primary keys to find the entities to delete.*/
    @Delete
    public void delete(MoneyEntity item);

    @Query("SELECT * FROM moneycare WHERE id = :id")
    public MoneyEntity getByID(int id);

    @Query("DELETE FROM moneycare WHERE id = :id")
    public void deleteByID(int id);

    @Query("SELECT * FROM moneycare")
    public Cursor getAll();

    /** 獲得一天的資料 */
    @Query("SELECT * FROM moneycare WHERE myDate"
            + " GLOB ''||:today||'*' ORDER BY myDate "
            + " ASC ")
    @TypeConverters({Converters.class})
    public List<MoneyEntity> getDailyData(Calendar today);
//    public Cursor getDailyData2(Calendar today);
}