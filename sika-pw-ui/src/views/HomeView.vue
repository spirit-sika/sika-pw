<script setup lang="ts">
import {useLoginHook} from '@/hooks/LoginHook';

import {useUserStore} from "@/stores/UserStore";

const userStore = useUserStore();

const {handleLogout} = useLoginHook();

const showMessage = () => {
  ElMessage({
    type:'success',
    message: userStore.getUserToken()
  })
}
</script>

<template>
  <div>
    <el-button @click="handleLogout">退出登录</el-button>
    <el-button @click="showMessage">提示消息</el-button>
  </div>
</template>
