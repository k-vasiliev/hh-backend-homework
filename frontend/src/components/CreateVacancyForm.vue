<template>
  <el-dialog
    title="Создание вакансии"
    :visible.sync="dialogVisible"
    width="30%"
    :before-close="handleClose">
    <el-form ref="createVacancyForm" :model="form" :rules="rules">
      <el-form-item label="Заголовок вакансии" prop="vacancyTitle">
        <el-input v-model="form.vacancyTitle"/>
      </el-form-item>
      <el-form-item label="Компания" prop="companyId">
        <el-select v-model="form.companyId" placeholder="Select">
          <el-option
            v-for="company in companies"
            :key="company.id"
            :label="company.name"
            :value="company.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Зарплата" prop="vacancySalary">
        <el-input-number v-model="form.vacancySalary" :step="5000"/>
      </el-form-item>
      <el-form-item label="Описание вакансии" prop="vacancyDescription">
        <el-input
          type="textarea"
          :autosize="{ minRows: 3, maxRows: 20}"
          placeholder="Описание вакансии"
          v-model="form.vacancyDescription">
        </el-input>
      </el-form-item>
      <el-form-item label="Контакты" prop="contacts">
        <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 5}"
          placeholder="Контакты"
          v-model="form.contacts">
        </el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onCreateUserClick">Создать</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { createVacancy } from './../api/vacancy'
  import { getCompanies } from '../api/company'

  export default {
    name: 'CreateVacancyForm',
    data () {
      return {
        dialogVisible: false,
        form: {
          vacancyTitle: '',
          companyId: null,
          vacancySalary: null,
          vacancyDescription: null,
          contacts: ''
        },
        rules: {
          vacancyTitle: [
            { required: true, message: 'Заполните заголовок вакансии', trigger: 'blur' }
          ],
          vacancyDescription: [
            { required: true, message: 'Заполните описание вакансии', trigger: 'blur' }
          ],
          companyId: [
            { required: true, message: 'Выберите пользователя', trigger: 'change' }
          ],
          contacts: [
            { required: true, message: 'Заполните контакты', trigger: 'blur' }
          ]
        },
        companies: []
      }
    },
    methods: {
      open () {
        this.dialogVisible = true
        this.loadCompanies()
      },
      clearForm () {
        this.$refs['createVacancyForm'].resetFields()
      },
      handleClose (done) {
        this.clearForm()
        done()
      },
      onCreateUserClick () {
        this.$refs['createVacancyForm'].validate((valid) => {
          if (valid) {
            createVacancy({
              title: this.form.vacancyTitle,
              companyId: this.form.companyId,
              salary: this.form.vacancySalary == null || this.form.vacancySalary === 0 ? null : this.form.vacancySalary,
              description: this.form.vacancyDescription,
              contacts: this.form.contacts
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
      loadCompanies () {
        getCompanies()
          .then(response => {
            this.companies = response.data
          })
      }
    }
  }
</script>

<style scoped>

</style>
