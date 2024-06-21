<script setup lang="ts">
import { useLoginHook } from '@/hooks/LoginHook';
import {ref} from "vue";
const {registerDTO, handleRegister, resetRegister} = useLoginHook()
import {emailSuffix} from "@/utils/email";

// 表单清空处理
import type { FormInstance } from 'element-plus'
const registerFormRef = ref<FormInstance>()
const resetForm = (formRef: FormInstance | undefined) => {
  if(!formRef) return
  formRef.resetFields()
  resetRegister()
}

// 构建邮箱输入提示
const completeEmail = (queryString: string, cb: any) => {
  if(!queryString) return cb([])
  cb(emailSuffix.map(item => ({value:queryString + item})))
}
</script>

<template>
  <div class="register-page">
    <div class="shell">
      <el-form
        ref="registerFormRef"
        :model="registerDTO"
        label-width="auto"
        style="max-width: 600px"
      >
        <el-form-item label="nickname" class="ipt-item">
          <el-input v-model="registerDTO.nickname" />
        </el-form-item>
        <el-form-item label="phone" class="ipt-item">
          <el-input v-model="registerDTO.phoneNumber" type="number" maxlength="11"/>
        </el-form-item>
        <el-form-item label="password" class="ipt-item">
          <el-input v-model="registerDTO.password" type="password"/>
        </el-form-item>
        <el-form-item label="email" class="ipt-item">
          <el-autocomplete
            v-model="registerDTO.email"
            :fetch-suggestions="completeEmail"
            clearable
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="sex" class="ipt-item">
          <el-radio-group v-model="registerDTO.sex" class="ml-4">
            <el-radio :value="0" size="large">私密</el-radio>
            <el-radio :value="1" size="large">男</el-radio>
            <el-radio :value="2" size="large">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item class="ipt-item">
          <el-button type="primary" @click="handleRegister">Sign In</el-button>
          <el-button @click="resetForm(registerFormRef)">reset</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style scoped>
.register-page {
  background: linear-gradient(45deg, #FBDA61, #FF5ACD);
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;

  .shell {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.2);
    width: 70vw;
    height: 70vh;
    border-radius: 15px;

    @media (max-width: 750px) {
      :deep(.ipt-item) {
        height: 5vh;
        line-height: 5vh;
      }
      :deep(.el-input__inner) {
        height: 5vh;
      }
      :deep(.el-input) {
        width: 30vw;
      }
    }
    :deep(.ipt-item) {
      height: 4vh;
      line-height: 4vh;
    }
    :deep(.el-input__inner) {
      height: 4vh;
    }
    :deep(.el-input) {
      width: 20vw;
    }
  }
}
</style>