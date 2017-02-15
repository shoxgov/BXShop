package com.sq.bxstore.net.response;

import java.util.List;

import com.sq.bxstore.net.BXBaseResponse;

/*
{
    "message": "连接接口成功", 
    "result": "0", 
    "data": [
        {
            "createTime": 1483275651000, 
            "gradeBalance": 100, 
            "gradeFcLevel": 0.01, 
            "gradeFcLevel1": 0.05, 
            "gradeFcLevel2": 0.03, 
            "gradeFcLevel3": 0.02, 
            "gradeName": "VIP1", 
            "gradeTxBalance": 100, 
            "id": 1, 
            "optionAdminid": 0
        }, 
        {
            "createTime": 1483275655000, 
            "gradeBalance": 200, 
            "gradeFcLevel": 0.02, 
            "gradeFcLevel1": 0.05, 
            "gradeFcLevel2": 0.03, 
            "gradeFcLevel3": 0.02, 
            "gradeName": "VIP2", 
            "gradeTxBalance": 200, 
            "id": 2, 
            "optionAdminid": 0
        }, 
        {
            "createTime": 1483275659000, 
            "gradeBalance": 300, 
            "gradeFcLevel": 0.03, 
            "gradeFcLevel1": 0.05, 
            "gradeFcLevel2": 0.03, 
            "gradeFcLevel3": 0.02, 
            "gradeName": "VIP3", 
            "gradeTxBalance": 300, 
            "id": 3, 
            "optionAdminid": 0
        }, 
        {
            "createTime": 1483275699000, 
            "gradeBalance": 400, 
            "gradeFcLevel": 0.04, 
            "gradeFcLevel1": 0.05, 
            "gradeFcLevel2": 0.03, 
            "gradeFcLevel3": 0.02, 
            "gradeName": "VIP4", 
            "gradeTxBalance": 400, 
            "id": 4, 
            "optionAdminid": 0
        }, 
        {
            "createTime": 1483275702000, 
            "gradeBalance": 500, 
            "gradeFcLevel": 0.05, 
            "gradeFcLevel1": 0.05, 
            "gradeFcLevel2": 0.03, 
            "gradeFcLevel3": 0.02, 
            "gradeName": "VIP5", 
            "gradeTxBalance": 500, 
            "id": 5, 
            "optionAdminid": 0
        }, 
        {
            "createTime": 1483275706000, 
            "gradeBalance": 600, 
            "gradeFcLevel": 0.06, 
            "gradeFcLevel1": 0.05, 
            "gradeFcLevel2": 0.03, 
            "gradeFcLevel3": 0.02, 
            "gradeName": "VIP6", 
            "gradeTxBalance": 600, 
            "id": 6, 
            "optionAdminid": 0
        }, 
        {
            "createTime": 1483275710000, 
            "gradeBalance": 700, 
            "gradeFcLevel": 0.07, 
            "gradeFcLevel1": 0.05, 
            "gradeFcLevel2": 0.03, 
            "gradeFcLevel3": 0.02, 
            "gradeName": "VIP7", 
            "gradeTxBalance": 700, 
            "id": 7, 
            "optionAdminid": 0
        }, 
        {
            "createTime": 1483275713000, 
            "gradeBalance": 800, 
            "gradeFcLevel": 0.08, 
            "gradeFcLevel1": 0.05, 
            "gradeFcLevel2": 0.03, 
            "gradeFcLevel3": 0.02, 
            "gradeName": "VIP8", 
            "gradeTxBalance": 800, 
            "id": 8, 
            "optionAdminid": 0
        }, 
        {
            "createTime": 1483275717000, 
            "gradeBalance": 900, 
            "gradeFcLevel": 0.09, 
            "gradeFcLevel1": 0.05, 
            "gradeFcLevel2": 0.03, 
            "gradeFcLevel3": 0.02, 
            "gradeName": "VIP9", 
            "gradeTxBalance": 900, 
            "id": 9, 
            "optionAdminid": 0
        }, 
        {
            "createTime": 1483275728000, 
            "gradeBalance": 1000, 
            "gradeFcLevel": 0.1, 
            "gradeFcLevel1": 0.05, 
            "gradeFcLevel2": 0.03, 
            "gradeFcLevel3": 0.02, 
            "gradeName": "VIP10", 
            "gradeTxBalance": 1000, 
            "id": 10, 
            "optionAdminid": 0
        }
    ], 
    "ver": "v1.0"
}
*/
public class AccountGradeLevelResponse extends BXBaseResponse {
	
	private List<GradeLevelInfo> data;
	
	public List<GradeLevelInfo> getData() {
		return data;
	}

	public void setData(List<GradeLevelInfo> data) {
		this.data = data;
	}

	public class GradeLevelInfo {
		private long createTime;
		private int gradeBalance;
		private int gradeTxBalance;
		private int id;
		private int optionAdminid;
		private String gradeName;
		private float gradeFcLevel;
		private float gradeFcLevel1;
		private float gradeFcLevel2;
		private float gradeFcLevel3;

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}

		public int getGradeBalance() {
			return gradeBalance;
		}

		public void setGradeBalance(int gradeBalance) {
			this.gradeBalance = gradeBalance;
		}

		public int getGradeTxBalance() {
			return gradeTxBalance;
		}

		public void setGradeTxBalance(int gradeTxBalance) {
			this.gradeTxBalance = gradeTxBalance;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getOptionAdminid() {
			return optionAdminid;
		}

		public void setOptionAdminid(int optionAdminid) {
			this.optionAdminid = optionAdminid;
		}

		public String getGradeName() {
			return gradeName;
		}

		public void setGradeName(String gradeName) {
			this.gradeName = gradeName;
		}

		public float getGradeFcLevel() {
			return gradeFcLevel;
		}

		public void setGradeFcLevel(float gradeFcLevel) {
			this.gradeFcLevel = gradeFcLevel;
		}

		public float getGradeFcLevel1() {
			return gradeFcLevel1;
		}

		public void setGradeFcLevel1(float gradeFcLevel1) {
			this.gradeFcLevel1 = gradeFcLevel1;
		}

		public float getGradeFcLevel2() {
			return gradeFcLevel2;
		}

		public void setGradeFcLevel2(float gradeFcLevel2) {
			this.gradeFcLevel2 = gradeFcLevel2;
		}

		public float getGradeFcLevel3() {
			return gradeFcLevel3;
		}

		public void setGradeFcLevel3(float gradeFcLevel3) {
			this.gradeFcLevel3 = gradeFcLevel3;
		}

	}
}
