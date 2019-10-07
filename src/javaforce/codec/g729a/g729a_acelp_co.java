/* g729a_acelp_co - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package javaforce.codec.g729a;

final class g729a_acelp_co implements g729a_constants {

  static void g729a_fc_search(g729a_encode_internal var_g729a_encode_internal, int i, float f) {
    float[] fs = var_g729a_encode_internal.c;
    float[] fs_0_ = var_g729a_encode_internal.h;
    float[] fs_1_ = var_g729a_encode_internal.x;
    float[] fs_2_ = var_g729a_encode_internal.y;
    float[] fs_3_ = new float[40];
    float[] fs_4_ = new float[40];
    float[] fs_5_ = new float[40];
    float[] fs_6_ = new float[40];
    float[] fs_7_ = new float[40];
    float f_8_ = var_g729a_encode_internal.gp;
    float[] fs_9_ = new float[616];
    float[] fs_10_ = new float[8];
    float f_11_ = 0.0F;
    int i_12_ = 0;
    int i_13_ = 0;
    int i_14_ = 0;
    int i_15_ = 0;
    int i_16_ = 0;
    int i_17_ = 0;
    int i_18_ = 0;
    int[] is = new int[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4,
      4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7};
    g729a_sinthesis_filter var_g729a_sinthesis_filter = new g729a_sinthesis_filter();
    int i_19_ = 0;
    int i_20_ = i_19_ + 8;
    int i_21_ = i_20_ + 8;
    int i_22_ = i_21_ + 8;
    int i_23_ = i_22_ + 8;
    int i_24_ = i_23_ + 8;
    int i_25_ = i_24_ + 64;
    int i_26_ = i_25_ + 64;
    int i_27_ = i_26_ + 64;
    int i_28_ = i_27_ + 64;
    int i_29_ = i_28_ + 64;
    int i_30_ = i_29_ + 64;
    int i_31_ = i_30_ + 64;
    int i_32_ = i_31_ + 64;
    if (f < 0.2000122F) {
      f = 0.2000122F;
    }
    if (f > 0.7944946F) {
      f = 0.7944946F;
    }
    for (int i_33_ = 0; i_33_ < 40; i_33_++) {
      fs_6_[i_33_] = fs_0_[i_33_];
    }
    for (int i_34_ = i; i_34_ < 40; i_34_++) {
      fs_6_[i_34_] = fs_6_[i_34_] + f * fs_6_[i_34_ - i];
    }
    for (int i_35_ = 0; i_35_ < 40; i_35_++) {
      fs_3_[40 - i_35_ - 1] = fs_1_[i_35_] - f_8_ * fs_2_[i_35_];
    }
    g729a_common.g729a_IIR(fs_5_, fs_3_, var_g729a_encode_internal.a_gamma,
            var_g729a_sinthesis_filter);
    for (int i_36_ = 0; i_36_ < 40; i_36_++) {
      fs_4_[i_36_] = fs_5_[40 - i_36_ - 1];
    }
    for (int i_37_ = 0; i_37_ < 40 - i; i_37_++) {
      fs_4_[i_37_] = fs_4_[i_37_] + f * fs_4_[i_37_ + i];
    }
    for (int i_38_ = 0; i_38_ < 40; i_38_++) {
      fs_7_[i_38_] = (float) (fs_4_[i_38_] < 0.0F ? -1 : 1);
      fs_4_[i_38_] = Math.abs(fs_4_[i_38_]);
    }
    g729a_cor_h(fs_9_, fs_6_, fs_7_);
    float f_39_ = -1.0F;
    float f_40_ = 1.0F;
    int i_41_ = i_26_;
    int i_42_ = i_29_;
    int i_43_ = i_31_;
    int i_44_ = i_22_;
    for (int i_45_ = 3; i_45_ < 5; i_45_++) {
      float f_46_ = -1.0F;
      float f_47_ = 1.0F;
      int i_48_ = -1;
      for (int i_49_ = 0; i_49_ < 2; i_49_++) {
        float f_50_ = -1.0F;
        for (int i_51_ = 2; i_51_ < 40; i_51_ += 5) {
          if (fs_4_[i_51_] > f_50_ && i_48_ != i_51_) {
            f_50_ = fs_4_[i_51_];
            i_12_ = i_51_;
          }
        }
        i_48_ = i_12_;
        int i_52_ = is[i_12_];
        int i_53_ = i_21_ + i_52_;
        float f_54_ = fs_4_[i_12_];
        float f_55_ = fs_9_[i_53_];
        i_53_ = i_43_ + i_52_ * 8;
        int i_56_ = i_44_;
        for (int i_57_ = i_45_; i_57_ < 40; i_57_ += 5) {
          float f_58_ = f_54_ + fs_4_[i_57_];
          float f_59_ = f_58_ * f_58_;
          float f_60_ = f_55_ + fs_9_[i_53_] + fs_9_[i_56_];
          i_53_++;
          i_56_++;
          if (f_47_ * f_59_ > f_46_ * f_60_) {
            f_46_ = f_59_;
            f_11_ = f_58_;
            f_47_ = f_60_;
            i_13_ = i_12_;
            i_14_ = i_57_;
          }
        }
      }
      i_12_ = i_13_;
      int i_61_ = i_14_;
      float f_62_ = f_11_;
      float f_63_ = f_47_;
      f_46_ = -1.0F;
      f_47_ = 1.0F;
      int i_64_ = i_28_ + is[i_12_];
      int i_65_ = i_42_ + is[i_61_];
      int i_66_ = i_20_;
      int i_67_ = 0;
      for (int i_68_ = 1; i_68_ < 40; i_68_ += 5) {
        fs_10_[i_67_] = fs_9_[i_64_] + fs_9_[i_65_] + fs_9_[i_66_];
        i_64_ += 8;
        i_65_ += 8;
        i_66_++;
        i_67_++;
      }
      i_64_ = i_25_ + is[i_12_];
      i_65_ = i_41_ + is[i_61_];
      i_66_ = i_19_;
      i_67_ = i_24_;
      for (int i_69_ = 0; i_69_ < 40; i_69_ += 5) {
        float f_70_ = f_62_ + fs_4_[i_69_];
        float f_71_ = f_63_ + fs_9_[i_64_] + fs_9_[i_65_] + fs_9_[i_66_];
        i_64_ += 8;
        i_65_ += 8;
        i_66_++;
        int i_72_ = 0;
        for (int i_73_ = 1; i_73_ < 40; i_73_ += 5) {
          float f_74_ = f_70_ + fs_4_[i_73_];
          float f_75_ = f_74_ * f_74_;
          float f_76_ = f_71_ + fs_9_[i_67_] + fs_10_[i_72_];
          i_67_++;
          i_72_++;
          if (f_47_ * f_75_ > f_46_ * f_76_) {
            f_46_ = f_75_;
            f_47_ = f_76_;
            i_13_ = i_69_;
            i_14_ = i_73_;
          }
        }
      }
      if (f_40_ * f_46_ > f_39_ * f_47_) {
        f_39_ = f_46_;
        f_40_ = f_47_;
        i_17_ = i_12_;
        i_18_ = i_61_;
        i_15_ = i_13_;
        i_16_ = i_14_;
      }
      f_46_ = -1.0F;
      f_47_ = 1.0F;
      i_48_ = -1;
      for (int i_77_ = 0; i_77_ < 2; i_77_++) {
        float f_78_ = -1.0F;
        for (int i_79_ = i_45_; i_79_ < 40; i_79_ += 5) {
          if (fs_4_[i_79_] > f_78_ && i_48_ != i_79_) {
            f_78_ = fs_4_[i_79_];
            i_12_ = i_79_;
          }
        }
        i_48_ = i_12_;
        int i_80_ = is[i_12_];
        i_64_ = i_44_ + i_80_;
        float f_81_ = fs_4_[i_12_];
        float f_82_ = fs_9_[i_64_];
        i_64_ = i_41_ + i_80_;
        i_65_ = i_19_;
        for (i_61_ = 0; i_61_ < 40; i_61_ += 5) {
          float f_83_ = f_81_ + fs_4_[i_61_];
          float f_84_ = f_83_ * f_83_;
          float f_85_ = f_82_ + fs_9_[i_64_] + fs_9_[i_65_];
          i_64_ += 8;
          i_65_++;
          if (f_47_ * f_84_ > f_46_ * f_85_) {
            f_46_ = f_84_;
            f_11_ = f_83_;
            f_47_ = f_85_;
            i_13_ = i_12_;
            i_14_ = i_61_;
          }
        }
      }
      i_12_ = i_13_;
      i_61_ = i_14_;
      f_62_ = f_11_;
      f_63_ = f_47_;
      f_46_ = -1.0F;
      f_47_ = 1.0F;
      i_64_ = i_43_ + is[i_12_];
      i_65_ = i_25_ + is[i_61_] * 8;
      i_66_ = i_21_;
      i_67_ = 0;
      for (int i_86_ = 2; i_86_ < 40; i_86_ += 5) {
        fs_10_[i_67_] = fs_9_[i_64_] + fs_9_[i_65_] + fs_9_[i_66_];
        i_64_ += 8;
        i_65_++;
        i_66_++;
        i_67_++;
      }
      i_64_ = i_42_ + is[i_12_];
      i_65_ = i_24_ + is[i_61_] * 8;
      i_66_ = i_20_;
      i_67_ = i_28_;
      for (int i_87_ = 1; i_87_ < 40; i_87_ += 5) {
        float f_88_ = f_62_ + fs_4_[i_87_];
        float f_89_ = f_63_ + fs_9_[i_64_] + fs_9_[i_65_] + fs_9_[i_66_];
        i_64_ += 8;
        i_65_++;
        i_66_++;
        int i_90_ = 0;
        for (int i_91_ = 2; i_91_ < 40; i_91_ += 5) {
          float f_92_ = f_88_ + fs_4_[i_91_];
          float f_93_ = f_92_ * f_92_;
          float f_94_ = f_89_ + fs_9_[i_67_] + fs_10_[i_90_];
          i_67_++;
          i_90_++;
          if (f_47_ * f_93_ > f_46_ * f_94_) {
            f_46_ = f_93_;
            f_47_ = f_94_;
            i_13_ = i_87_;
            i_14_ = i_91_;
          }
        }
      }
      if (f_40_ * f_46_ > f_39_ * f_47_) {
        f_39_ = f_46_;
        f_40_ = f_47_;
        i_18_ = i_12_;
        i_15_ = i_61_;
        i_16_ = i_13_;
        i_17_ = i_14_;
      }
      i_41_ = i_27_;
      i_42_ = i_30_;
      i_43_ = i_32_;
      i_44_ = i_23_;
    }
    int i_95_ = fs_7_[i_15_] < 0.0F ? 0 : 1;
    int i_96_ = fs_7_[i_16_] < 0.0F ? 0 : 1;
    int i_97_ = fs_7_[i_17_] < 0.0F ? 0 : 1;
    int i_98_ = fs_7_[i_18_] < 0.0F ? 0 : 1;
    var_g729a_encode_internal.S = (short) (i_95_ + 2 * i_96_ + 4 * i_97_ + 8 * i_98_);
    int i_99_ = is[i_18_];
    int i_100_ = i_18_ - i_99_ * 5 == 3 ? 0 : 1;
    var_g729a_encode_internal.C = (short) (is[i_15_] + 8 * is[i_16_] + 64 * is[i_17_]
            + 512 * (2 * i_99_ + i_100_));
    g729a_utils.g729a_set_0(fs, 0, 40);
    fs[i_15_] = fs_7_[i_15_];
    fs[i_16_] = fs_7_[i_16_];
    fs[i_17_] = fs_7_[i_17_];
    fs[i_18_] = fs_7_[i_18_];
    for (i_99_ = i; i_99_ < 40; i_99_++) {
      fs[i_99_] += f * fs[i_99_ - i];
    }
  }

  static void g729a_cor_h(float[] fs, float[] fs_101_, float[] fs_102_) {
    int i = 0;
    int i_103_ = i + 8;
    int i_104_ = i_103_ + 8;
    int i_105_ = i_104_ + 8;
    int i_106_ = i_105_ + 8;
    int i_107_ = i_106_ + 8;
    int i_108_ = i_107_ + 64;
    int i_109_ = i_108_ + 64;
    int i_110_ = i_109_ + 64;
    int i_111_ = i_110_ + 64;
    int i_112_ = i_111_ + 64;
    int i_113_ = i_112_ + 64;
    int i_114_ = i_113_ + 64;
    int i_115_ = i_114_ + 64;
    float f = 0.0F;
    int i_116_ = 0;
    for (int i_117_ = 7; i_117_ >= 0; i_117_--) {
      f += fs_101_[i_116_] * fs_101_[i_116_];
      fs[i_106_ + i_117_] = 0.5F * f;
      f += fs_101_[i_116_ + 1] * fs_101_[i_116_ + 1];
      fs[i_105_ + i_117_] = 0.5F * f;
      f += fs_101_[i_116_ + 2] * fs_101_[i_116_ + 2];
      fs[i_104_ + i_117_] = 0.5F * f;
      f += fs_101_[i_116_ + 3] * fs_101_[i_116_ + 3];
      fs[i_103_ + i_117_] = 0.5F * f;
      f += fs_101_[i_116_ + 4] * fs_101_[i_116_ + 4];
      fs[i + i_117_] = 0.5F * f;
      i_116_ += 5;
    }
    int i_118_ = 1;
    i_107_ += 63;
    i_108_ += 63;
    i_109_ += 63;
    i_110_ += 63;
    i_111_ += 63;
    i_112_ += 63;
    i_113_ += 63;
    i_114_ += 63;
    i_115_ += 63;
    for (int i_119_ = 0; i_119_ < 8; i_119_++) {
      f = 0.0F;
      float f_120_ = 0.0F;
      float f_121_ = 0.0F;
      float f_122_ = 0.0F;
      i_116_ = 0;
      int i_123_ = 0;
      int i_124_ = i_119_ * 7 - 1;
      float f_125_ = fs_101_[i_118_ + i_116_];
      float f_126_ = fs_101_[i_118_ + i_116_ + 1];
      float f_127_ = fs_101_[i_118_ + i_116_ + 2];
      for (int i_128_ = i_119_ + 1; i_128_ < 8; i_128_++) {
        float f_129_ = fs_101_[i_116_];
        float f_130_ = fs_101_[i_116_ + 1];
        float f_131_ = fs_101_[i_116_ + 2];
        float f_132_ = fs_101_[i_116_ + 3];
        float f_133_ = fs_101_[i_116_ + 4];
        float f_134_ = fs_101_[i_118_ + i_116_ + 3];
        float f_135_ = fs_101_[i_118_ + i_116_ + 4];
        float f_136_ = fs_101_[i_118_ + i_116_ + 5];
        float f_137_ = fs_101_[i_118_ + i_116_ + 6];
        float f_138_ = fs_101_[i_118_ + i_116_ + 7];
        f += f_129_ * f_125_;
        f_120_ += f_129_ * f_126_;
        fs[i_115_ + i_123_] = f_120_;
        f_121_ += f_129_ * f_127_;
        fs[i_113_ + i_123_] = f_121_;
        f_122_ += f_129_ * f_134_;
        fs[i_110_ + i_123_] = f_122_;
        f += f_130_ * f_126_;
        fs[i_114_ + i_123_] = f;
        f_120_ += f_130_ * f_127_;
        fs[i_112_ + i_123_] = f_120_;
        f_121_ += f_130_ * f_134_;
        fs[i_109_ + i_123_] = f_121_;
        f_122_ += f_130_ * f_135_;
        f += f_131_ * f_127_;
        fs[i_111_ + i_123_] = f;
        f_120_ += f_131_ * f_134_;
        fs[i_108_ + i_123_] = f_120_;
        f_121_ += f_131_ * f_135_;
        fs[i_115_ + i_123_ + i_124_] = f_121_;
        f_122_ += f_131_ * f_136_;
        fs[i_114_ + i_123_ + i_124_] = f_122_;
        f += f_132_ * f_134_;
        fs[i_107_ + i_123_] = f;
        f_120_ += f_132_ * f_135_;
        fs[i_113_ + i_123_ + i_124_] = f_120_;
        f_121_ += f_132_ * f_136_;
        fs[i_112_ + i_123_ + i_124_] = f_121_;
        f_122_ += f_132_ * f_137_;
        fs[i_111_ + i_123_ + i_124_] = f_122_;
        f += f_133_ * f_135_;
        fs[i_110_ + i_123_ + i_124_] = f;
        f_120_ += f_133_ * f_136_;
        fs[i_109_ + i_123_ + i_124_] = f_120_;
        f_121_ += f_133_ * f_137_;
        fs[i_108_ + i_123_ + i_124_] = f_121_;
        f_122_ += f_133_ * f_138_;
        fs[i_107_ + i_123_ + i_124_] = f_122_;
        f_125_ = f_136_;
        f_126_ = f_137_;
        f_127_ = f_138_;
        i_123_ -= 9;
        i_116_ += 5;
      }
      float f_139_ = fs_101_[i_116_];
      float f_140_ = fs_101_[i_116_ + 1];
      float f_141_ = fs_101_[i_116_ + 2];
      float f_142_ = fs_101_[i_116_ + 3];
      float f_143_ = fs_101_[i_118_ + i_116_ + 3];
      f += f_139_ * f_125_;
      f_120_ += f_139_ * f_126_;
      fs[i_115_ + i_123_] = f_120_;
      f_121_ += f_139_ * f_127_;
      fs[i_113_ + i_123_] = f_121_;
      f_122_ += f_139_ * f_143_;
      fs[i_110_ + i_123_] = f_122_;
      f += f_140_ * f_126_;
      fs[i_114_ + i_123_] = f;
      f_120_ += f_140_ * f_127_;
      fs[i_112_ + i_123_] = f_120_;
      f_121_ += f_140_ * f_143_;
      fs[i_109_ + i_123_] = f_121_;
      f += f_141_ * f_127_;
      fs[i_111_ + i_123_] = f;
      f_120_ += f_141_ * f_143_;
      fs[i_108_ + i_123_] = f_120_;
      f += f_142_ * f_143_;
      fs[i_107_ + i_123_] = f;
      i_118_ += 5;
      i_107_ -= 8;
      i_108_ -= 8;
      i_109_ -= 8;
      i_110_ -= 8;
      i_111_ -= 8;
      i_112_ -= 8;
      i_113_ -= 8;
      i_114_ -= 8;
      i_115_ -= 8;
    }
    i_107_ = i_106_ + 8;
    i_108_ = i_107_ + 64;
    i_109_ = i_108_ + 64;
    i_110_ = i_109_ + 64;
    i_111_ = i_110_ + 64;
    i_112_ = i_111_ + 64;
    i_113_ = i_112_ + 64;
    i_114_ = i_113_ + 64;
    i_115_ = i_114_ + 64;
    for (int i_144_ = 0; i_144_ < 40; i_144_ += 5) {
      float f_145_ = fs_102_[i_144_];
      float f_146_ = fs_102_[i_144_ + 1];
      float f_147_ = fs_102_[i_144_ + 2];
      for (int i_148_ = 0; i_148_ < 40; i_148_ += 5) {
        float f_149_ = fs_102_[i_148_ + 1];
        float f_150_ = fs_102_[i_148_ + 2];
        float f_151_ = fs_102_[i_148_ + 3];
        float f_152_ = fs_102_[i_148_ + 4];
        fs[i_107_] *= f_145_ * f_149_;
        i_107_++;
        fs[i_108_] *= f_145_ * f_150_;
        i_108_++;
        fs[i_109_] *= f_145_ * f_151_;
        i_109_++;
        fs[i_110_] *= f_145_ * f_152_;
        i_110_++;
        fs[i_111_] *= f_146_ * f_150_;
        i_111_++;
        fs[i_112_] *= f_146_ * f_151_;
        i_112_++;
        fs[i_113_] *= f_146_ * f_152_;
        i_113_++;
        fs[i_114_] *= f_147_ * f_151_;
        i_114_++;
        fs[i_115_] *= f_147_ * f_152_;
        i_115_++;
      }
    }
  }
}