<template>
  <el-dialog
    title="Добавление отклика"
    :visible.sync="dialogVisible"
    width="30%"
    :before-close="handleClose">
    <el-form ref="createNegotiationForm" :model="form" :rules="rules">
      <el-form-item label="Резюме" prop="resumeId">
        <el-select v-model="form.resumeId" placeholder="Select">
          <el-option
            v-for="resume in resumes"
            :key="resume.id"
            :label="resume.title"
            :value="resume.id">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onCreateUserClick">Создать</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { createNegotiation } from '../api/negotiation'
  import { getResumes } from '../api/resume'

  export default {
    name: 'CreateNegotiationForm',
    data () {
      return {
        dialogVisible: false,
        vacancyId: null,
        form: {
          resumeId: null
        },
        rules: {
          resumeId: [
            { required: true, message: 'Выберите пользователя', trigger: 'change' }
          ]
        },
        resumes: []
      }
    },
    methods: {
      open (vacancyId) {
        this.vacancyId = vacancyId
        this.loadResumes()
      },
      clearForm () {
        this.$refs['createNegotiationForm'].resetFields()
      },
      handleClose (done) {
        this.clearForm()
        done()
      },
      onCreateUserClick () {
        this.$refs['createNegotiationForm'].validate((valid) => {
          if (valid) {
            createNegotiation({
              resumeId: this.form.resumeId,
              vacancyId: this.vacancyId,
            })
              .then(() => {
                this.dialogVisible = false
                this.clearForm()
              })
          } else {
            return false
          }
        })
      },
      loadResumes () {
        getResumes()
          .then(response => {
            this.resumes = response.data
            this.dialogVisible = true
          })
      }
    }
  }
</script>

<style scoped>

</style>
