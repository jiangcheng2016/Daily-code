using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySystem.Model;
using MySql.Data.MySqlClient;
using System.Data;

namespace MySystem.DAL
{
    /// <summary>
    /// 工站数据操作类
    /// </summary>
    public class WorkCellService
    {
        /// <summary>
        /// 插入工站
        /// </summary>
        /// <param name="workCell"></param>
        /// <returns></returns>
        public static int InsertWorkCell(WorkCell workCell)
        {
            int result = 0;
            string sql = "insert into workCell(cellName,remark) values(@CellName,@Remark);";

            try
            {
                result = DBHelper.ExecuteNonQuery(DBHelper.ConnectionString, CommandType.Text, sql, new MySqlParameter[] {
                new MySqlParameter("@CellName",workCell.CellName),
                new MySqlParameter("@Remark",workCell.Remark)
            });
            }
            catch (Exception ex)
            {

                throw ex;
            }
            return result;
        }
        /// <summary>
        /// 更新工站
        /// </summary>
        /// <param name="workCell"></param>
        /// <returns></returns>
        public static int UpdateWorkCell(WorkCell workCell)
        {
            int result = 0;
            string sql = "update workCell set cellName=@CellName,remark=@Remark where cellId=@CellId";
            try
            {
                result = DBHelper.ExecuteNonQuery(DBHelper.ConnectionString, CommandType.Text, sql, new MySqlParameter[]
                {
                    new MySqlParameter("@CellName",workCell.CellName),
                    new MySqlParameter("@Remark",workCell.Remark),
                    new MySqlParameter("@CellId",workCell.CellId)
                });
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return result;
        }
        /// <summary>
        /// 根据Id删除
        /// </summary>
        /// <param name="cellId"></param>
        /// <returns></returns>
        public static int DeleteWorkCell(int cellId)
        {
            int result = 0;
            string sql = "delete from workCell where cellId=@CellId";
            try
            {
                result = DBHelper.ExecuteNonQuery(DBHelper.ConnectionString, CommandType.Text, sql,
                    new MySqlParameter("@CellId",cellId)
                );
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return result;
        }
        /// <summary>
        /// 查询所有的工站
        /// </summary>
        /// <returns></returns>
        public static List<WorkCell> GetAllWorkCells()
        {
            List<WorkCell> workCells = new List<WorkCell>();
            string sql = "select * from workCell";
            try
            {
                using (MySqlDataReader dr = DBHelper.ExecuteReader(DBHelper.ConnectionString, CommandType.Text, sql))
                {
                    WorkCell workCell = null;
                    while (dr.Read())
                    {
                        workCell = new WorkCell();
                        if (Convert.ToInt32(dr["cellId"]) != 0)
                        {
                            workCell.CellId = Convert.ToInt32(dr["cellId"]);
                        }
                        if (!(dr["cellName"] is DBNull))
                        {
                            workCell.CellName = dr["cellName"].ToString();
                        }
                        if (!(dr["remark"] is DBNull))
                        {
                            workCell.Remark = dr["remark"].ToString();
                        }
                        workCells.Add(workCell);
                    }
                }
            }
            catch (Exception ex)
            {

                throw ex;
            }
            return workCells;
        }
        /// <summary>
        /// 根据id查询工站
        /// </summary>
        /// <param name="cId"></param>
        /// <returns></returns>
        public static WorkCell GetWorkCellById(int cId)
        {
            WorkCell workCell = null;
            string sql = "select * from workCell where cellId=@cId";
            MySqlParameter pCId = new MySqlParameter("@cId", cId);

            try
            {
                using (MySqlDataReader dr = DBHelper.ExecuteReader(DBHelper.ConnectionString, CommandType.Text, sql, pCId))
                {
                    if (dr.Read())
                    {
                        workCell = new WorkCell();
                        if(Convert.ToInt32(dr["cellId"])!=0)
                        {
                            workCell.CellId = Convert.ToInt32(dr["cellId"]);
                        }
                        if(!(dr["cellName"] is DBNull))
                        {
                            workCell.CellName = dr["cellName"].ToString();
                        }
                        if (!(dr["remark"] is DBNull))
                        {
                            workCell.Remark = dr["remark"].ToString();
                        }
                            
                    }
                }
            }
            catch (Exception ex)
            {

                throw ex;
            }
            return workCell;
        }
        /// <summary>
        /// 根据工站名称查询信息
        /// </summary>
        /// <param name="cName"></param>
        /// <returns></returns>
        public static List<WorkCell> GetWorkCellsByConds(string cName)
        {
            List<WorkCell> workCells = new List<WorkCell>();
            string sql = "select * from workCell where cellName like @cellName";
            MySqlParameter par = new MySqlParameter("@cellName", "%" + cName + "%");
            try
            {
                using (MySqlDataReader dr = DBHelper.ExecuteReader(DBHelper.ConnectionString, CommandType.Text, sql,par))
                {
                    WorkCell workCell = null;
                    while (dr.Read())
                    {
                        workCell = new WorkCell();
                        if (Convert.ToInt32(dr["cellId"]) != 0)
                        {
                            workCell.CellId = Convert.ToInt32(dr["cellId"]);
                        }
                        if (!(dr["cellName"] is DBNull))
                        {
                            workCell.CellName = dr["cellName"].ToString();
                        }
                        if (!(dr["remark"] is DBNull))
                        {
                            workCell.Remark = dr["remark"].ToString();
                        }
                        workCells.Add(workCell);
                    }
                }
            }
            catch (Exception ex)
            {

                throw ex;
            }
            return workCells;
        }
    }
}
