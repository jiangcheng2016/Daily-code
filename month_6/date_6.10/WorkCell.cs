using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MySystem.Model
{
    /// <summary>
    /// 工站类
    /// 2018-5-12 zhangsan
    /// </summary>
    public class WorkCell
    {
        /// <summary>
        /// ID
        /// </summary>
        public int CellId { get; set; }
        /// <summary>
        /// 工站名称
        /// </summary>
        public string CellName { get; set; }
        /// <summary>
        /// 备注
        /// </summary>
        public string Remark { get; set; }
    }
}
