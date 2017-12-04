package com.qg.recruit.service.impl;

import com.qg.recruit.domain.Student;
import com.qg.recruit.domain.StudentRepository;
import com.qg.recruit.dto.Result;
import com.qg.recruit.enums.NumberEnum;
import com.qg.recruit.enums.RegexEnum;
import com.qg.recruit.enums.StateEnum;
import com.qg.recruit.exception.RecruitException;
import com.qg.recruit.service.RecruitService;
import com.qg.recruit.utils.WordUtil;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author 郑俊铭
 * Date: 2017/12/2
 * Time: 10:04 No struggle, talent how to match the willfulness.
 * Description: QG招新网站逻辑层接口
 */
@Service
public class RecruitServiceImpl implements RecruitService {
	private static String path = Objects.requireNonNull(ClassUtils.getDefaultClassLoader().getResource("")).getPath();
	private static final String SOURCE_PATH = path.substring(0, path.indexOf("target"));
	private final StudentRepository studentRepository;

	@Autowired
	public RecruitServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Result judgeStudentSignUpByStudentId(String studentId) {
		if (!studentId.matches(RegexEnum.STUDENT_ID_REGEX.getRegex())) {
			// 学号不匹配
			throw new RecruitException(StateEnum.STUDENT_ID_FORMAT_ERROR);
		} else if (studentRepository.findByStudentId(studentId) != null) {
			// 该学生已经报名过，没法再次报名
			throw new RecruitException(StateEnum.STUDENT_HAS_SIGN_UP);
		} else {
			// 学生没有报名过
			return new Result(StateEnum.STUDENT_HAS_NOT_SIGN_UP);
		}
	}

	@Override
	public Result getStudentInfoByStudentId(String studentId) {
		return new Result<>(StateEnum.OK, studentRepository.findByStudentId(studentId));
	}

	@Override
	public Result insertRegistrationInfo(Student student) {

		// 名字的最大长度
		int maximumNameLength = 15;
		// 最小成绩
		int minimumScore = 0;
		// 最大成绩
		int maximumScore = 100;
		// 年级的最大长度
		int maximumGradeLength = 5;
		// 专业班级的最大长度
		int maximumClassLength = 20;
		// C语言实验成绩最大长度
		int maximumCTestScoreLength = 5;

		if ("".equals(student.getName()) || "".equals(student.getStudentId()) || "".equals(student.getSex() + "")
				|| "".equals(student.getGrade()) || "".equals(student.getaClass()) || "".equals(student.getPhone())
				|| "".equals(student.getGpa() + "") || "".equals(student.getFail() + "")
				|| "".equals(student.getcScore() + "") || "".equals(student.getcTestScore())
				|| "".equals(student.getEnScore() + "") || "".equals(student.getWish() + "")
				|| "".equals(student.getSwap() + "")) {
			// 参数为空
			throw new RecruitException(StateEnum.PARAM_IS_EMPTY);
		} else if (student.getName().length() > maximumNameLength) {
			// 姓名长度过长，15个字为最大长度
			throw new RecruitException(StateEnum.NAME_LENGTH_IS_TOO_LONG);
		} else if (!student.getStudentId().matches(RegexEnum.STUDENT_ID_REGEX.getRegex())) {
			// 学号格式错误或者年级不对
			throw new RecruitException(StateEnum.STUDENT_ID_FORMAT_ERROR);
		} else if (studentRepository.findByStudentId(student.getStudentId()) != null) {
			// 该学生已经报名过，没法再次报名
			throw new RecruitException(StateEnum.STUDENT_HAS_SIGN_UP);
		} else if (!(student.getSex() == NumberEnum.ONE.getNumber()
				|| student.getSex() == NumberEnum.ZERO.getNumber())) {
			// 性别不对，主要是为了防止别人攻击
			throw new RecruitException(StateEnum.SEX_ERROR);
		} else if (student.getGrade().length() > maximumGradeLength) {
			// 年级的长度超过8个字符
			throw new RecruitException(StateEnum.GRADE_LENGTH_IS_TOO_LONG);
		} else if (student.getaClass().length() > maximumClassLength) {
			// 专业班级的长度超过20个字符
			throw new RecruitException(StateEnum.CLASS_LENGTH_IS_TOO_LONG);
		} else if (!student.getPhone().matches(RegexEnum.PHONE_REGEX.getRegex())) {
			// 手机的格式不对
			throw new RecruitException(StateEnum.PHONE_FORMAT_ERROR);
		} else if (!String.valueOf(student.getGpa()).matches(RegexEnum.GPA_REGEX.getRegex()) || student.getGpa() < 0.0
				|| student.getGpa() > 5.0) {
			// 绩点的格式不对，只支持整数、一个小数、两个小数,并且在0.0到5.0之间
			throw new RecruitException(StateEnum.GPA_FORMAT_ERROR);
		} else if (!(student.getFail() == NumberEnum.ZERO.getNumber()
				|| student.getFail() == NumberEnum.ONE.getNumber())) {
			// 跟性别类型作用
			throw new RecruitException(StateEnum.FAIL_ERROR);
		} else if (student.getcScore() < minimumScore || student.getcScore() > maximumScore
				|| student.getEnScore() < minimumScore || student.getEnScore() > maximumScore) {
			// 成绩不在范围之内
			throw new RecruitException(StateEnum.SCORE_OUT_OF_SIZE);
		} else if (student.getcTestScore().length() > maximumCTestScoreLength) {
			// C语言实验成绩长度大于5
			throw new RecruitException(StateEnum.C_TEST_SCORE_IS_TOO_LONG);
		} else if (!(student.getWish() == NumberEnum.ONE.getNumber() || student.getWish() == NumberEnum.TWO.getNumber()
				|| student.getWish() == NumberEnum.THREE.getNumber() || student.getWish() == NumberEnum.FOUR.getNumber()
				|| student.getWish() == NumberEnum.FIVE.getNumber() || student.getWish() == NumberEnum.SIX.getNumber()
				|| student.getWish() == NumberEnum.SEVEN.getNumber())) {
			// 作用与性别类似
			throw new RecruitException(StateEnum.WISH_ERROR);
		} else if (!(student.getSwap() == NumberEnum.ZERO.getNumber()
				|| student.getSwap() == NumberEnum.ONE.getNumber())) {
			// 作用与性别类似
			throw new RecruitException(StateEnum.SWAP_ERROR);
		} else {
			// 一切正常
			studentRepository.save(student);
			return new Result(StateEnum.OK);
		}
	}

	@Override
	public Result getPagingData(Integer page) {
		// 一页的数量
		int pageSize = 2;
		Page<Student> students = studentRepository.findAll(new PageRequest(page, pageSize));
		// 存放返回数据data
		Map<String, Object> map = new HashMap<>();
		// 存放所有Student的部分数据
		List<Map<String, Object>> list = new ArrayList<>();
		for (Student student : students.getContent()) {
			// 存放单个Student的部分数据
			Map<String, Object> infoMap = new HashMap<>();
			infoMap.put("studentId", student.getStudentId());
			infoMap.put("name", student.getName());
			infoMap.put("sex", student.getSex());
			infoMap.put("aClass", student.getaClass());
			list.add(infoMap);
		}
		map.put("students", list);
		// 总数量
		map.put("totalElements", students.getTotalElements());
		// 总页数
		map.put("totalPages", students.getTotalPages());
		return new Result<>(StateEnum.OK, map);
	}

	@Override
	public Result deleteStudentByStudentId(List<Map<String, String>> list) {
		List<Student> students = new ArrayList<>();
		for (Map<String, String> map : list) {
			Student student = studentRepository.findByStudentId(map.get("studentId"));
			students.add(student);
		}
		studentRepository.delete(students);
		return new Result(StateEnum.OK);
	}

	@Override
	public Result selectByStudentId(String studentId) {
		if ("".equals(studentId)) {
			// 参数为空
			throw new RecruitException(StateEnum.PARAM_IS_EMPTY);
		} else if (!studentId.matches(RegexEnum.STUDENT_ID_REGEX.getRegex())) {
			// 学号格式错误或者年级不对
			throw new RecruitException(StateEnum.STUDENT_ID_FORMAT_ERROR);
		} else {
		    Student student = studentRepository.findByStudentId(studentId);
		    if (student == null) {
		    	// 没有找到该学生
		        throw new RecruitException(StateEnum.DO_NOT_FIND_STUDENT);
            } else {
		    	// 找到则返回
		    	return new Result<>(StateEnum.OK, student);
			}
        }
	}

	@Override
	public Result exportWordByStudentId(List<Map<String, String>> list, HttpServletResponse response) throws IOException, Docx4JException {
		if (list.size() == 0) {
			// 参数为空
			throw new RecruitException(StateEnum.PARAM_IS_EMPTY);
		} else {
			// 文件名List
			List<String> fileList = new ArrayList<>();
			for (Map<String, String> map : list) {
				// 查找学生信息
				Student student = studentRepository.findByStudentId(map.get("studentId"));
				Map<String, String> dataMap = new HashMap<>();
				dataMap.put("name", student.getName());
				dataMap.put("sex", student.getSex() == 1 ? "男" : "女");
				dataMap.put("grade", student.getGrade());
				dataMap.put("aClass", student.getaClass());
				dataMap.put("fail", student.getFail() == 1 ? "有" : "无");
				dataMap.put("cScore", String.valueOf(student.getcScore()));
				dataMap.put("cTestScore", student.getcTestScore());
				dataMap.put("enScore", String.valueOf(student.getEnScore()));
				dataMap.put("gpa", String.valueOf(student.getGpa()));
				dataMap.put("phone", student.getPhone());
				// 导出Word文档
				WordUtil.exportWord(dataMap, map.get("studentId") + ".docx");
				fileList.add(SOURCE_PATH + "word/" + map.get("studentId") + ".docx");
			}
			// 合并Word文档
			String mergeFilePath = SOURCE_PATH + "word/" + UUID.randomUUID().toString() + ".docx";
			WordUtil.mergeDoc(fileList, mergeFilePath);

			// 把word发给前端
			try {
				OutputStream out = response.getOutputStream();
				InputStream in = new BufferedInputStream(new
						FileInputStream(mergeFilePath));
				int temp;
				while ((temp = in.read()) != -1) {
					out.write(temp);
				}
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 删除子Word文档
			for (String filename : fileList) {
				new File(filename).delete();
			}
			new File(mergeFilePath).delete();
			return new Result(StateEnum.OK);
		}
	}
}
