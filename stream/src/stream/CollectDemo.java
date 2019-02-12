package stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections4.MapUtils;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * ѧ�� ����
 */
@Data
@ToString
@AllArgsConstructor
class Student {
	/**
	 * ����
	 */
	private String name;

	/**
	 * ����
	 */
	private int age;

	/**
	 * �Ա�
	 */
	private Gender gender;

	/**
	 * �༶
	 */
	private Grade grade;
}

/**
 * �Ա�
 */
enum Gender {
	MALE, FEMALE
}

/**
 * �༶
 */
enum Grade {
	ONE, TWO, THREE, FOUR
}

/**
 * @author sss
 */
public class CollectDemo {

	public static void main(String[] args) {
		// ��������
		List<Student> students = Arrays.asList(
				new Student("С��", 10, Gender.MALE, Grade.ONE),
				new Student("����", 9, Gender.MALE, Grade.THREE),
				new Student("С��", 8, Gender.FEMALE, Grade.TWO),
				new Student("С��", 13, Gender.FEMALE, Grade.FOUR),
				new Student("С��", 7, Gender.FEMALE, Grade.THREE),
				new Student("С��", 13, Gender.MALE, Grade.ONE),
				new Student("С��", 13, Gender.FEMALE, Grade.THREE),
				new Student("С��", 9, Gender.FEMALE, Grade.TWO),
				new Student("С��", 6, Gender.MALE, Grade.ONE),
				new Student("С��", 6, Gender.MALE, Grade.ONE),
				new Student("С��", 14, Gender.FEMALE, Grade.FOUR),
				new Student("С��", 13, Gender.MALE, Grade.FOUR));

		// �õ�����ѧ���������б�
		// s -> s.getAge() �滻�� Student::getAge�������� , ���������һ������ lambda$0�����ĺ���
//        Set<Integer> ages = students.stream().map(s -> s.getAge())
//                .collect(Collectors.toSet());
		Set<Integer> ages = students.stream().map(Student::getAge)
				.collect(Collectors.toCollection(TreeSet::new));
		System.out.println("����ѧ��������:" + ages);

		// ͳ�ƻ�����Ϣ
		IntSummaryStatistics agesSummaryStatistics = students.stream()
				.collect(Collectors.summarizingInt(Student::getAge));
		System.out.println("���������Ϣ:" + agesSummaryStatistics);

		// �ֿ�
		Map<Boolean, List<Student>> genders = students.stream().collect(
				Collectors.partitioningBy(s -> s.getGender() == Gender.MALE));
//		// System.out.println("��Ůѧ���б�:" + genders);
		MapUtils.verbosePrint(System.out, "��Ůѧ���б�", genders);

		// ����
		Map<Grade, List<Student>> grades = students.stream()
				.collect(Collectors.groupingBy(Student::getGrade));
		MapUtils.verbosePrint(System.out, "ѧ���༶�б�", grades);
//
//		// �õ����а༶ѧ���ĸ���
//		Map<Grade, Long> gradesCount = students.stream().collect(Collectors
//				.groupingBy(Student::getGrade, Collectors.counting()));
//		MapUtils.verbosePrint(System.out, "�༶ѧ�������б�", gradesCount);

	}

}
