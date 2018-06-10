using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySystem.Model;
using MySystem.BLL;
namespace MySystemUI
{
    public partial class FrmWorCell : Form
    {
        public FrmWorCell()
        {
            InitializeComponent();
        }

        private void FrmWorCell_Load(object sender, EventArgs e)
        {
            this.dgvInfo.AutoGenerateColumns = false;
            this.dgvInfo.DataSource = WorkCellManager.GetWorkCellByConditions("");
        }

        private void btnSelect_Click(object sender, EventArgs e)
        {
            
            this.dgvInfo.DataSource = WorkCellManager.GetWorkCellByConditions(this.txtNameS.Text);
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (checkInfo())
            {
                WorkCell workCell = new WorkCell();
                workCell.CellName = this.txtNo.Text.Trim();
                workCell.Remark = this.txtName.Text.Trim();
                if (this.lblId.Text != "")
                {
                    workCell.CellId = Convert.ToInt32(this.lblId.Text.Trim());
                }
                bool flag = WorkCellManager.SaveWorkCell(workCell);
                if (flag)
                {
                    MessageBox.Show("保存成功！");
                }
                else
                {
                    MessageBox.Show("保存失败！");
                }
                this.dgvInfo.DataSource = WorkCellManager.GetWorkCellByConditions(this.txtNameS.Text);
            }
        }
        private bool checkInfo()
        {
            return true;
        }

        private void tsBtnDel_Click(object sender, EventArgs e)
        {
            string message = null;
            if (this.dgvInfo.SelectedRows.Count == 0)
            {
                MessageBox.Show("请选择要删除的行！");
                return;
            }
            int flag = WorkCellManager.DeleteWorkCell(Convert.ToInt32(this.dgvInfo.SelectedRows[0].Cells[0].Value));
            switch (flag)
            {
                case 0:
                    message = "删除失败！";
                    break;
                case 1:
                    message = "删除成功！";
                    break;
                case 2:
                    message = "该工人已经关联数据，不能删除！";
                    break;

            }
            MessageBox.Show(message);
            this.dgvInfo.DataSource = WorkCellManager.GetWorkCellByConditions(this.txtNameS.Text);
        }

        private void dgvInfo_CellContentDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            this.lblId.Text = this.dgvInfo.Rows[e.RowIndex].Cells["colId"].Value.ToString();
            this.txtNo.Text = this.dgvInfo.Rows[e.RowIndex].Cells["colCellName"].Value.ToString();
            this.txtName.Text = this.dgvInfo.Rows[e.RowIndex].Cells["colRemark"].Value.ToString();
        }

        private void dgvInfo_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            
            this.lblId.Text = this.dgvInfo.Rows[e.RowIndex].Cells["colId"].Value.ToString();
            this.txtNo.Text = this.dgvInfo.Rows[e.RowIndex].Cells["colCellName"].Value.ToString();
            this.txtName.Text = this.dgvInfo.Rows[e.RowIndex].Cells["colRemark"].Value.ToString();
        }

        private void tlbtnUpdate_Click(object sender, EventArgs e)
        {

        }
    }
}
