<template>
  <el-dialog
    title="Создание пользователя"
    :visible.sync="dialogVisible"
    width="30%"
    :before-close="handleClose">
    <el-form ref="userCreateForm" :model="form" :rules="rules">
      <el-form-item label="Имя пользователя" prop="userName">
        <el-input v-model="form.userName"/>
      </el-form-item>
      <el-form-item label="Тип пользователя">
        <el-radio-group v-model="form.userType">
          <el-radio size="medium" label="APPLICANT" border>Соискатель</el-radio>
          <el-radio size="medium" label="EMPLOYER" border>Работодатель</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onCreateUserClick">Создать</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { createUser } from './../api/user'

  export default {
    name: 'CreateUserForm',
    data () {
      return {
        dialogVisible: false,
        form: {
          userName: '',
          userType: 'APPLICANT',
        },
        rules: {
          userName: [
            { required: true, message: 'Заполните имя пользователя', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      open () {
        this.dialogVisible = true
      },
      clearForm () {
        this.$refs['userCreateForm'].resetFields()
      },
      handleClose (done) {
        this.clearForm()
        done()
      },
      onCreateUserClick () {
        this.$refs['userCreateForm'].validate((valid) => {
          if (valid) {
            createUser({
              name: this.form.userName,
              type: this.form.userType
            })
              .then(() => {
                this.clear()
                this.dialogVisible = false
              })
              .finally(() => this.clearForm())
          } else {
            return false
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
