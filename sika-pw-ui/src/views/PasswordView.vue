<script setup lang="ts">
import {usePasswordHooks} from "@/hooks/PasswordHooks";
import {Delete, Plus, Search, SwitchButton} from '@element-plus/icons-vue'
import type {FormInstance} from "element-plus";

const dialogForm = ref<FormInstance>()
const {
  searchForm,
  passwordList,
  pageInfo,
  title,
  open,
  passwordDTO,
  copy,
  getPassword,
  resetSearchFormAndGetPassword,
  resetDialogForm,
  submitForm,
  handleSizeChange,
  handleCurrentChange,
  handleUpdate,
  handleDelete
} = usePasswordHooks()


const openDialog = () => {
  title.value = "添加密码";
  open.value = true;
}
const closeDialog = () => {
  resetDialogForm(dialogForm.value)
  open.value = false;
}
const passwordCol = ref<HTMLDivElement>()
const accountCol = ref<HTMLDivElement>()
</script>

<template>
  <div class="password-page">
    <div class="search-container">
      <el-form v-model="searchForm" label-width="auto">
        <el-form-item label="域名或ip" prop="domainName">
          <el-input placeholder="域名或ip" v-model="searchForm.domainName" @keyup.enter="getPassword()"/>
        </el-form-item>
        <el-form-item label="用户名" prop="account">
          <el-input placeholder="用户名"  v-model="searchForm.account" @keyup.enter="getPassword()"/>
        </el-form-item>
        <el-form-item label="模糊查询" prop="condition">
          <el-input placeholder="输入域名或用户名"  v-model="searchForm.condition" @keyup.enter="getPassword()"/>
        </el-form-item>
      </el-form>
      <div class="search-operator-container">
        <el-button type="primary" :icon="Search" @click="getPassword" />
        <el-button type="primary" :icon="Delete" @click="resetSearchFormAndGetPassword" />
      </div>
    </div>

    <el-button @click="openDialog" :icon="Plus" type="primary"></el-button>

    <div  class="data-table-wrapper">
      <el-table :data="passwordList" height="500px" scrollbar-always-on id="data-list">
        <el-table-column prop="domainName" label="域名" width="180" align="center"/>
        <el-table-column
          prop="account"
          label="用户名"
          width="180"
          align="center"
        >
          <template #default="scope">
            <div class="enable-copy" ref="accountCol" @click="copy(scope.row.account, '账号复制成功')">{{scope.row.account}}</div>
          </template>
        </el-table-column>
        <el-table-column
          prop="password"
          label="密码"
          align="center"
        >
          <template #default="scope">
            <div class="enable-copy" ref="passwordCol" @click="copy(scope.row.password, '密码复制成功')">{{scope.row.password}}</div>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="添加时间" align="center"/>
        <el-table-column prop="updateTime" label="最后修改时间" align="center"/>
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-button type="primary" @click="copy(`账号: ${scope.row.account}\n密码: ${scope.row.password}`, '账号密码复制成功')">一键复制</el-button>
            <el-button type="primary" @click="handleUpdate(scope.row)">修改</el-button>
            <el-button type="danger" @click="handleDelete(scope.row.pwId, scope.row.account)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>


    <el-pagination
        v-model:current-page="searchForm.pageNum"
        v-model:page-size="searchForm.pageSize"
        :page-sizes="[10, 20, 30, 50, 100]"
        :size="pageInfo?.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageInfo?.total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />

    <!-- 添加\修改 -->
    <el-dialog
      :title="title"
      v-model="open"
      width="600px"
      append-to-body
      :show-close="false"
    >
      <el-form :model="passwordDTO" ref="dialogForm">
        <el-form-item prop="domainName" label="域名">
          <el-input v-model="passwordDTO.domainName" placeholder=""/>
        </el-form-item>
        <el-form-item prop="account" label="账户">
          <el-input v-model="passwordDTO.account" placeholder=""/>
        </el-form-item>
        <el-form-item prop="password" label="密码">
          <el-input v-model="passwordDTO.password" placeholder=""/>
        </el-form-item>
        <el-form-item>
          <el-button :icon="Plus" @click="submitForm"/>
          <el-button :icon="SwitchButton" @click="closeDialog"/>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<style scoped lang="scss">
.password-page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.search-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 1vh;

  .search-operator-container {
    margin-left: 5vw;
  }
}


.data-table-wrapper {
  width: 99vw;
  overflow: auto;


  .data-list {
    width: 99vw;
  }

  .enable-copy {
    background-color: rgba(0, 0, 0, 0.1);
    cursor: pointer;
  }

  .password-operator {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-content: center;
  }
}
</style>
