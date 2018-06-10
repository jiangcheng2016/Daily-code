using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySystem.Model;
using MySystem.DAL;

namespace MySystem.BLL
{
    public class WorkCellManager
    {
        /// <summary>
        /// 保存
        /// </summary>
        /// <param name="workCell"></param>
        /// <returns></returns>
        public static bool SaveWorkCell(WorkCell workCell)
        {
            bool flag = true;
            try
            {
                int result = 0;
                if (workCell.CellId > 0)
                {
                    result = WorkCellService.UpdateWorkCell(workCell);
                }
                else
                {
                    result = WorkCellService.InsertWorkCell(workCell);
                }

                if (result == 0)
                {
                    flag = false;
                }
            }
            catch (Exception)
            {

                flag = false;
            }
            return flag;
        }
        public static int DeleteWorkCell(int cId)
        {
            //0:错误  1：成功  2：已被关联
            int flag = 1;
            try
            {
                int result = WorkCellService.DeleteWorkCell(cId);
                if (result == 0)
                {
                    flag = 0;
                }
            }
            catch (Exception)
            {

                flag = 2;
            }
            return flag;
        }
        public static List<WorkCell> GetWorkCellByConditions(string cName)
        {
            List<WorkCell> workCells = new List<WorkCell>();
            if (cName == null || cName == "")
            {
                workCells = WorkCellService.GetAllWorkCells();
            }
            else
            {
                workCells = WorkCellService.GetWorkCellsByConds(cName);
            }
            return workCells;
        }
        public static WorkCell GetWorkCellById(int id)
        {
            return WorkCellService.GetWorkCellById(id);
        }
    }
}
