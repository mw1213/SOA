package questionnaire;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean( name = "Questionnaire")
@SessionScoped
public class QuestionnaireBean {
    String name;
    String email;
    Integer age;
    String gender;
    String education;
    Double height;
    String breastCircumference;
    String cupSize;
    String waist;
    String hips;
    String chest;
    String belt;
    String canSpend;
    String buyingFrequency;
    String preferredColors;
    String preferredKindOfClothing;
    Boolean subviewW=false, subviewM=false, dataSubmitted=false;
    String spendingMonthly;
    String spendingFrequency;
    List<String> preferedColors;
    List<String> preferedKind;

    public void setDataSubmitted(Boolean dataSubmitted) {
        this.dataSubmitted = dataSubmitted;
    }

    public Boolean getDataSubmitted() {
        return dataSubmitted;
    }

    public void setSpendingMonthly(String spendingMonthly) {
        this.spendingMonthly = spendingMonthly;
    }

    public void setSpendingFrequency(String spendingFrequency) {
        this.spendingFrequency = spendingFrequency;
    }


    public String getSpendingMonthly() {
        return spendingMonthly;
    }

    public String getSpendingFrequency() {
        return spendingFrequency;
    }

    public void setPreferedColors(List<String> preferedColors) {
        this.preferedColors = preferedColors;
    }

    public void setPreferedKind(List<String> preferedKind) {
        this.preferedKind = preferedKind;
    }

    public List<String> getPreferedColors() {
        return preferedColors;
    }

    public List<String> getPreferedKind() {
        return preferedKind;
    }

    public void setSubviewW(Boolean subviewW) {
        this.subviewW = subviewW;
    }

    public void setSubviewM(Boolean subviewM) {
        this.subviewM = subviewM;
    }

    public Boolean getSubviewW() {
        return subviewW;
    }

    public Boolean getSubviewM() {
        return subviewM;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setCanSpend(String canSpend) {
        this.canSpend = canSpend;
    }

    public void setBuyingFrequency(String buyingFrequency) {
        this.buyingFrequency = buyingFrequency;
    }

    public void setPreferredColors(String preferredColors) {
        this.preferredColors = preferredColors;
    }

    public void setPreferredKindOfClothing(String preferredKindOfClothing) {
        this.preferredKindOfClothing = preferredKindOfClothing;
    }

    public String getCanSpend() {
        return canSpend;
    }

    public String getBuyingFrequency() {
        return buyingFrequency;
    }

    public String getPreferredColors() {
        return preferredColors;
    }

    public String getPreferredKindOfClothing() {
        return preferredKindOfClothing;
    }

    public void setBreastCircumference(String breastCircumference) {
        this.breastCircumference = breastCircumference;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public void setHips(String hips) {
        this.hips = hips;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public void setBelt(String belt) {
        this.belt = belt;
    }

    public String getBreastCircumference() {
        return breastCircumference;
    }

    public String getCupSize() {
        return cupSize;
    }

    public String getWaist() {
        return waist;
    }

    public String getHips() {
        return hips;
    }

    public String getChest() {
        return chest;
    }

    public String getBelt() {
        return belt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public String getEducation() {
        return education;
    }

    public Double getHeight() {
        return height;
    }

    public void renderAdditionalQuestions(){
        if(gender.contentEquals("Female")){
            subviewM=false;
            subviewW=true;
        } else if(gender.contentEquals("Male")){
            subviewW=false;
            subviewM=true;
        } else {
            subviewW=false;
            subviewM=false;
        }
    }

    public void submitData(){
        dataSubmitted = true;
    }
}
