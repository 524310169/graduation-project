package qcj.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qcj.graduation.mapper.SignInMapper;
import qcj.graduation.mapper.StudentMapper;
import qcj.graduation.model.SignIn;
import qcj.graduation.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SignInMapper signInMapper;

    public List<Student> getSignInList() {
        List<Long> codes = signInMapper.getStudentCodes();
        List<Student> students = new ArrayList<Student>();
        for(Long code : codes){
            Student student = studentMapper.getStudentDetail(code);
            students.add(student);
        }
//        codes.stream().map(s -> students.add(studentMapper.getStudentDetail(s)));
        return students;
    }

    public Boolean isSuccess(Long code) {
        Boolean result = Boolean.FALSE;
        SignIn signIn = signInMapper.isSucess(code);
        if (signIn != null) {
            result = Boolean.TRUE;
        }
        return result;
    }

    public List<Student> getNotSignInList() {
        List<Long> classCode = signInMapper.getClassCode();
        List<Long> studentCodes = signInMapper.getStudentCodes();
        List<Student> notSignIn = studentMapper.getNotSignInList(classCode, studentCodes);
        return notSignIn;
    }

    public String listToString(List<Long> sources){
        StringBuffer sb = new StringBuffer();
        for(Long source : sources){
            sb.append(source).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
